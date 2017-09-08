package ar.com.flexibility.examen.domain.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;

/**
 * 
 * @author hackma
 * @version 0.1
 * Test para prueba unitaria de la clase @see ProductServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productServiceImpl;

	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private Product product;

	@Test
	public void createProductTest() {		
		productServiceImpl.createProduct(product);
		when(productServiceImpl.createProduct(product)).thenReturn(product);
		verify(productRepository).save(product);
	}
	
	@Test
	public void findAllProductsTest() {
		productServiceImpl.findAllProducts();
		when(productServiceImpl.findAllProducts()).thenReturn(new ArrayList<Product>());
		verify(productRepository).findAll();
	}
	
	@Test
	public void findProductById() {
		productServiceImpl.findProductById(product.getId());
		when(productServiceImpl.findProductById(product.getId())).thenReturn(product);
		verify(productRepository).findOne(product.getId());
	}
	
	@Test
	public void updateProductTest() {
		when(productRepository.exists(product.getId())).thenReturn(true);
		productServiceImpl.updateProduct(product);
		when(productRepository.save(product)).thenReturn(product);
		verify(productRepository).save(product);
	}
	
	@Test
	public void deleteProductTest() throws Exception {
		productServiceImpl.deleteProduct(product);
		verify(productRepository).delete(product);
	}
}
