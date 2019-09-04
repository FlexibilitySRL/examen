package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.flexibility.examen.app.errorCode.ProductErrorCode;
import ar.com.flexibility.examen.app.exception.BusinessException;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

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
			assertTrue(ex.getMessages()[0].equals(ProductErrorCode.PRODUCT_INVALID_CODE.name()));
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
			assertTrue(ex.getMessages()[0].equals(ProductErrorCode.PRODUCT_INVALID_NAME.name()));
			throw ex;
		}
	}
}
