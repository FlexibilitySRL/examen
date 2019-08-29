package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	private ProductRepository repo = Mockito.mock(ProductRepository.class);
	
	@InjectMocks
	private ProductServiceImpl service;
	
	@BeforeEach
	void initUseCase() {
		service = new ProductServiceImpl(repo);
	}
	
	@Test
	public void createAProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setName("notebook");
		product.setDescription("Dell 16gb");
		product.setPrice(50000.00);
		product.setStock(10);
		
		when(repo.save(any(Product.class))).thenReturn(product);
		Product result = service.create(product);
		verify(repo).save(product);

		assertNotNull(result);
		assertEquals(result.getId(), product.getId());
	}

	@Test
	public void updateAProduct() {
		Product originalProduct = new Product();
		originalProduct.setId(1L);
		originalProduct.setName("notebook");
		originalProduct.setDescription("Dell 16gb");
		originalProduct.setPrice(50000.00);
		originalProduct.setStock(10);
		
		Product productToUpdate = new Product();
		productToUpdate.setId(1L);
		productToUpdate.setPrice(100000.00);
		productToUpdate.setStock(20);
				
		when(repo.save(any(Product.class))).thenReturn(originalProduct).thenReturn(productToUpdate);
		when(repo.findOne(1L)).thenReturn(originalProduct);
		
		service.create(originalProduct);
		Product updatedProduct = service.update(productToUpdate);
		
		verify(repo).findOne(1L);
		verify(repo,times(2)).save(originalProduct);
				
		assertEquals(updatedProduct.getId(), originalProduct.getId());
		assertEquals(updatedProduct.getId(), productToUpdate.getId());
		assertEquals(updatedProduct.getPrice(), productToUpdate.getPrice(),0.1);
		assertEquals(updatedProduct.getStock(), productToUpdate.getStock());
		
	}
	
	@Test
	public void findById() {
		Product product = new Product();
		product.setId(1L);
		product.setName("notebook");
		product.setDescription("Dell 16gb");
		product.setPrice(50000.00);
		product.setStock(10);
		
		when(repo.findOne(1L)).thenReturn(product);
		Product result = service.findById(1L);
		verify(repo).findOne(1L);
		
		assertEquals(result, product);
	}
	
	@Test
	public void deleteById() {
		Product product = new Product();
		product.setId(1L);
		product.setName("notebook");
		product.setDescription("Dell 16gb");
		product.setPrice(50000.00);
		product.setStock(10);
				
		service.deleteById(1L);
		verify(repo).delete(1L);
	}
		
}
