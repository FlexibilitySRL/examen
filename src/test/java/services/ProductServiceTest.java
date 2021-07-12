package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.Repository.ProductRepository;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.ProductMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.examen.domain.validators.Validator;
import fixtures.ProductFixture;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

  @InjectMocks
  private ProductServiceImpl productService;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductMapper productMapper;

  @Mock
  private Validator validator;

  @Test
  public void findAllTest() {
    List<ProductApi> lsProducts = ProductFixture
        .getProducApitList(ProductFixture.getProductApiWithNameAndDescription("Notebook", "Lenovo"),
            ProductFixture.getProductApiWithNameAndDescription("Mouse", "Logitech"));
    when(this.productMapper.productsToListProductApi(this.productRepository.findAll()))
        .thenReturn(lsProducts);

    List<ProductApi> response = this.productService.findAll();
    assertEquals(lsProducts, response);
    verify(this.productRepository, times(2)).findAll();
  }

  @Test
  public void findByIdCheckedTest() {
    Product product = ProductFixture.getProductWithId(1L);
    when(this.productRepository.findById(1L)).thenReturn(Optional.of(product));

    ProductApi response = this.productService.findByIdChecked(1L);
    assertEquals(this.productMapper.productToProductApi(product), response);
    verify(this.productRepository, times(1)).findById(1L);
  }

  @Test
  public void findByIdChecked_NotFoundTest() {
    when(this.productRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(GenericNotFoundException.class, () -> {
      this.productService.findByIdChecked(1L);
    });

    assertTrue(exception.getMessage().contains("Product not found"));
    verify(this.productRepository, times(1)).findById(1L);
  }

  @Test
  public void save_successTest() {
    ProductApi productMock = ProductFixture.getProductApiWithId(1L);
    Product product = ProductFixture.getProduct();
    ProductApi productToSave = ProductFixture.getProductApi();
    when(this.productMapper.productToProductApi(this.productRepository.save(product))).thenReturn(productMock);
    doNothing().when(this.validator).validateProduct(productToSave, Boolean.FALSE);

    ProductApi response = this.productService.save(productToSave);
    assertEquals(productMock, response);
    verify(this.productRepository, times(1)).save(product);
  }

  @Test
  public void save_badRequestTest() {
    ProductApi productMock = ProductFixture.getProductApiWithId(1L);
    productMock.setName(null);
    Product product = ProductFixture.getProduct();
    GenericBadRequestException badRequestException = new GenericBadRequestException("The name is required");
    when(this.productMapper.productToProductApi(this.productRepository.save(product))).thenReturn(productMock);
    doThrow(badRequestException).when(this.validator).validateProduct(any(), any());

    Exception exception = assertThrows(GenericBadRequestException.class, () -> {
      this.productService.save(productMock);
    });

    assertTrue(exception.getMessage().contains("The name is required"));
    verify(this.productRepository, times(1)).save(product);
  }

  @Test
  public void update_successTest() {
    ProductApi productMock = ProductFixture.getProductApiWithId(1L);
    Product product = ProductFixture.getProductWithId(1L);
    ProductApi productToSave = ProductFixture.getProductApiWithId(1L);
    when(this.productMapper.productToProductApi(this.productRepository.save(product))).thenReturn(productMock);
    doNothing().when(this.validator).validateProduct(productToSave, Boolean.TRUE);
    when(this.productRepository.findById(1L)).thenReturn(Optional.of(product));

    ProductApi response = this.productService.update(productToSave);
    assertEquals(productMock, response);
    verify(this.productRepository, times(1)).save(product);
  }

  @Test
  public void update_badRequestTest() {
    ProductApi productMock = ProductFixture.getProductApiWithId(1L);
    Product product = ProductFixture.getProductWithId(1L);
    GenericBadRequestException badRequestException = new GenericBadRequestException("The id is required");
    when(this.productMapper.productToProductApi(this.productRepository.save(product))).thenReturn(productMock);
    when(this.productRepository.findById(1L)).thenReturn(Optional.of(product));
    doThrow(badRequestException).when(this.validator).validateProduct(any(), any());

    Exception exception = assertThrows(GenericBadRequestException.class, () -> {
      this.productService.update(productMock);
    });

    assertTrue(exception.getMessage().contains("The id is required"));
    verify(this.productRepository, times(1)).save(product);
  }

  @Test
  public void update_notFoundTest() {
    ProductApi productMock = ProductFixture.getProductApiWithId(1L);
    Product product = ProductFixture.getProductWithId(1L);
    GenericNotFoundException genericNotFoundException = new GenericNotFoundException("The id is required");
    when(this.productMapper.productToProductApi(this.productRepository.save(product))).thenReturn(productMock);
    when(this.productRepository.findById(1L)).thenReturn(Optional.of(product));
    doThrow(genericNotFoundException).when(this.validator).validateProduct(any(), any());

    Exception exception = assertThrows(GenericNotFoundException.class, () -> {
      this.productService.update(productMock);
    });

    assertTrue(exception.getMessage().contains("The id is required"));
    verify(this.productRepository, times(1)).save(product);
  }

  @Test
  public void deleteTest() {
    ProductApi productMock = ProductFixture.getProductApiWithId(1L);
    Product product = ProductFixture.getProductWithId(1L);
    when(this.productMapper.productToProductApi(this.productRepository.save(product))).thenReturn(productMock);
    when(this.productRepository.findById(1L)).thenReturn(Optional.of(product));

    this.productService.delete(1L);

    assertFalse(this.productService.findAll().contains(productMock));
    verify(this.productRepository, times(1)).deleteById(1L);
  }

  @Test
  public void deleteTest_notFound() {
    GenericNotFoundException genericNotFoundException = new GenericNotFoundException("Product not found");
    doThrow(genericNotFoundException).when(this.productRepository).findById(1L);

    Exception exception = assertThrows(GenericNotFoundException.class, () -> {
      this.productService.delete(1L);
    });

    assertTrue(exception.getMessage().contains("Product not found"));
    verify(this.productRepository, times(0)).deleteById(1L);
  }

}
