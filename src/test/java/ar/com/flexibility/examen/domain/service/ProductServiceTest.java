package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.flexibility.examen.app.errorCode.ProductErrorCode;
import ar.com.flexibility.examen.app.exception.BusinessException;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	public ProductServiceImpl productService;

	
	@Test
	public void whenDTOToEntityShouldReturnEquivalentEntity() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("Producto 3323"); 
		productDTO.setCode(1L);
		Product product = productService.dtoToEntity(productDTO);

		assertTrue(productDTO.getCode().equals(product.getCode()));
		assertTrue(productDTO.getName().equals(product.getName()));
	}

	@Test
	public void whenEntityToDTOShouldReturnEquivalentDTO() {
		Product product = new Product();
		product.setName("Producto 3323");
		product.setCode(1L);
		ProductDTO productDTO = productService.entityToDto(product);

		assertTrue(productDTO.getCode().equals(product.getCode()));
		assertTrue(productDTO.getName().equals(product.getName()));
	}

	@Test(expected = BusinessException.class)
	public void whenProductDTOWithInvalidCodeEntityToDTOShouldReturnErrorCode() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("Producto 3323");
		try {
			productService.dtoToEntity(productDTO);
		} catch (BusinessException ex) {
			assertTrue(ex.getMessages()[0].equals(ProductErrorCode.PRODUCT_INVALID_CODE.getDescription()));
			throw ex;
		}
	}

	@Test(expected = BusinessException.class)
	public void whenProductDTOWithInvalidNameEntityToDTOShouldReturnErrorCode() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setCode(1l);
		try {
			productService.dtoToEntity(productDTO);
		} catch (BusinessException ex) {
			assertTrue(ex.getMessages()[0].equals(ProductErrorCode.PRODUCT_INVALID_NAME.getDescription()));
			throw ex;
		}
	}
	
	@Test
	public void whenSaveThenReturnProduct() {
		// given
		Product newProduct = new Product();
		newProduct.setCode(1L);
		newProduct.setName("test-producto");
		ProductDTO givenProductDTO = productService.entityToDto(newProduct);
		
		doReturn(newProduct).when(productRepository).save(any(Product.class));
		
		// when
		ProductDTO dto = productService.save(givenProductDTO);
		
		// then
		assertTrue(dto.getName().equals(newProduct.getName()));
		assertTrue(dto.getCode().equals(newProduct.getCode()));
	}
	
	@Test
	public void whenFindByIdThenReturnProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setCode(11L);
		product.setName("test-producto");
		
		doReturn(product).when(productRepository).findOne(1L);

		ProductDTO dto = productService.findById(1L);

		assertTrue(dto.getId().equals(product.getId()));
		assertTrue(dto.getName().equals(product.getName()));
		assertTrue(dto.getCode().equals(product.getCode()));
	}
	
	@Test
	public void whenFindAllThenReturnAllProducts() {
		Product product = new Product();
		product.setId(1L);
		product.setCode(1L);
		product.setName("test-producto");
		
		List<ProductDTO> actualProducts = new ArrayList<ProductDTO>();
		List<Product> expectedProducts = new ArrayList<Product>();
		expectedProducts.add(product);
		
		doReturn(expectedProducts).when(productRepository).findAll();
		
		actualProducts = productService.findAll();
				
		assertTrue(actualProducts.get(0).getId().equals(expectedProducts.get(0).getId()));
		assertTrue(actualProducts.get(0).getCode().equals(expectedProducts.get(0).getCode()));
		assertTrue(actualProducts.get(0).getName().equals(expectedProducts.get(0).getName()));


	}
	
}
