package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void whenFindAll_thenReturnList() {
        List<Product> products = getDummyProducts();

        given(productRepository.findAll()).willReturn(products);

        List<Product> pulledProducts = productService.retrieveProducts();

        assertThat(pulledProducts).hasSize(products.size());
    }

    @Test
    public void whenFindExistingProduct_thenReturnProduct() {
        List<Product> products = getDummyProducts();

        given(productRepository.findOne(1L)).willReturn(products.get(0));

        Product foundProduct = productService.retrieveProductById(1L);

        assertThat(foundProduct).isEqualTo(products.get(0));
    }

    @Test
    public void whenFindNonExistingProduct_thenReturnNull() {
        given(productRepository.findOne(6L)).willReturn(null);

        Product foundProduct = productService.retrieveProductById(6L);

        assertThat(foundProduct).isEqualTo(null);
    }

    @Test
    public void whenAddingProduct_thenItHasAnId() {
        Product product = new Product("Coca-Cola", "A cold Coca-cola", 95f, 12);
        Product productWithId = new Product(1L,"Coca-Cola", "A cold Coca-cola", 95f, 12);
        List<Product> products = new ArrayList<>();

        given(productRepository.findAll()).willReturn(products);

        assertThat(productService.retrieveProducts()).hasSize(0);

        products.add(productWithId);

        given(productRepository.save(product)).willReturn(productWithId);

        Product retrievedProduct = productService.addProduct(product);

        given(productRepository.findAll()).willReturn(products);

        List<Product> retrievedProducts = productService.retrieveProducts();

        assertThat(retrievedProducts).hasSize(1);
        assertThat(retrievedProduct.getId()).isEqualTo(1L);
    }

    @Test
    public void whenUpdatingExistingProduct_thenItHasFieldsUpdated() {
        List<Product> products = getDummyProducts();

        Product originalProduct = products.get(0);
        Product updatedProduct = new Product(1L, "Coca-Cola", "A cold Coca-cola", 95f, 100);

        given(productRepository.exists(1L)).willReturn(true);
        given(productRepository.save(originalProduct)).willReturn(updatedProduct);

        Product retrievedProduct = productService.updateProduct(originalProduct.getId(), originalProduct);

        assertThat(retrievedProduct.getStock()).isEqualTo(100);
    }

    @Test
    public void whenUpdatingNonExistingProduct_thenReturnNull() {
        List<Product> products = getDummyProducts();

        Product product = new Product(4L, "Pepsi", "A cold Pepsi", 95f, 100);

        given(productRepository.exists(4L)).willReturn(false);

        Product retrievedProduct = productService.updateProduct(4L, product);

        assertThat(retrievedProduct).isEqualTo(null);
    }

    @Test
    public void whenDeletingExistingProduct_thenReturnTrue() {
        given(productRepository.exists(1L)).willReturn(true);

        assertThat(productService.deleteProduct(1L)).isTrue();
    }

    @Test
    public void whenDeletingNonExistingSeller_thenReturnFalse() {
        given(productRepository.exists(6L)).willReturn(false);

        assertThat(productService.deleteProduct(6L)).isFalse();
    }

    private List<Product> getDummyProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "Coca-Cola", "A cold Coca-cola", 95f, 12));
        products.add(new Product(2L, "Wine", "Red red wine", 100f, 100));
        products.add(new Product(3L, "Ice", "Just some ice", 17.5f, 100));

        return products;
    }
}