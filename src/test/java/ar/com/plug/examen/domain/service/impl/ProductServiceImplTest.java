package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.ValidatorsService;

public class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	ProductRepository productRepository;

	@Mock
	ConverterService converter;

	@Mock
	ValidatorsService validators;

	private Product product;
	private ProductApi productApi;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		product = new Product(1L, "Product A", 8.95D);
		productApi = new ProductApi(1L, "Product A", 8.95D);
	}
	
	@Test
	void testListAll() throws NotFoundException {
		when(converter.convertList(productRepository.findAll(), ProductApi.class)).thenReturn(Arrays.asList(productApi));
		List<ProductApi> found = productService.listAll();
		assertNotNull(found);
	}
	
	@Test
	void testFindById() throws NotFoundException {
		when(converter.convert(productRepository.findOneById(anyLong()), ProductApi.class)).thenReturn(productApi);
		ProductApi found = productService.findById(1L);
		assertNotNull(found);
		assertEquals(productApi, found);
	}

	@Test
	void testFindById_NotFoundException() throws NotFoundException {
		when(converter.convert(productRepository.findOneById(anyLong()), ProductApi.class)).thenReturn(null);
		assertThrows(NotFoundException.class, () -> {
			productService.findById(1L);
		});
	}

	@Test
	void testFindByName() {
		when(converter.convertList(productRepository.findByName(anyString()), ProductApi.class)).thenReturn(Arrays.asList(productApi));
		List<ProductApi> productApiList = productService.findByName("mock name");
		assertNotNull(productApiList);
		assertFalse(productApiList.isEmpty());
		assertTrue(productApiList.contains(productApi));
	}

	@Test
	void testSave() throws BadRequestException {
		ProductApi newProduct = new ProductApi("test product", 8.95D);
		when(productRepository.save(converter.convert(newProduct, Product.class))).thenReturn(product);
		when(converter.convert(product, ProductApi.class)).thenReturn(productApi);
		ProductApi saved = productService.save(newProduct);
		assertNotNull(saved);
		assertNull(newProduct.getId());
	}

	@Test
	void testSave_BadRequestException() throws BadRequestException {
		ProductApi newProduct = new ProductApi();
		when(validators.checkCompleteObject(newProduct, true)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			productService.save(newProduct);
		});
	}
	
	@Test
	void testDeleteById() throws NotFoundException, BadRequestException {
		when(productRepository.existsById(anyLong())).thenReturn(true);
		productService.deleteById(1L);
	}

	@Test
	void testDeleteById_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(product.getId(), false)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			productService.deleteById(product.getId());
		});
	}

	@Test
	void testDeleteById_NotFoundException() throws NotFoundException {
		when(productRepository.existsById(product.getId())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> {
			productService.deleteById(product.getId());
		});
	}

	@Test
	void testUpdate() throws NotFoundException, BadRequestException {
		ProductApi newProduct = new ProductApi(1L, "test product", 8.95D);
		when(productRepository.existsById(newProduct.getId())).thenReturn(true);
		when(productRepository.save(converter.convert(newProduct, Product.class))).thenReturn(product);
		when(converter.convert(product, ProductApi.class)).thenReturn(newProduct);
		ProductApi saved = productService.update(newProduct);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assertEquals(product.getId(), saved.getId());
		assertNotEquals(product.getName(), saved.getName());
	}

	@Test
	void testUpdate_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(productApi, false)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			productService.update(productApi);
		});
	}

	@Test
	void testUpdate_NotFoundException() throws NotFoundException {
		when(productRepository.existsById(product.getId())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> {
			productService.update(productApi);
		});
	}
}