package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;


import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {

	private ProductRepository repo = Mockito.mock(ProductRepository.class);
	
	@InjectMocks
	private ProductServiceImpl service;
	
	@BeforeEach
	void initUseCase() {
		service = new ProductServiceImpl(repo);
	}
	
	@Test
	void createAProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setName("notebook");
		product.setDescription("Dell 16gb");
		product.setPrice(50000.00);
		product.setStock(10);
		
		when(repo.save(any(Product.class))).thenReturn(product);
		Product result = service.create(product);
		verify(repo).save(product);
		
		assertEquals(result.getId(), product.getId());
	}
	
	
	@Test
	void updateAProduct() {
		Product originalProduct = new Product();
		originalProduct.setId(1L);
		originalProduct.setName("notebook");
		originalProduct.setDescription("Dell 16gb");
		originalProduct.setPrice(50000.00);
		originalProduct.setStock(10);
		
		Product produtToUpdate = new Product();
		produtToUpdate.setId(1L);
		produtToUpdate.setPrice(100000.00);
		produtToUpdate.setStock(20);
				
		when(repo.save(any(Product.class))).thenReturn(originalProduct).thenReturn(produtToUpdate);
		when(repo.getOne(1L)).thenReturn(originalProduct);
		
		service.create(originalProduct);
		Product updatedProduct = service.update(produtToUpdate);
		
		verify(repo).getOne(1L);
		verify(repo,times(2)).save(originalProduct);
				
		assertEquals(updatedProduct.getId(), originalProduct.getId());
		assertEquals(updatedProduct.getId(), produtToUpdate.getId());
		assertEquals(updatedProduct.getPrice(), produtToUpdate.getPrice(),0.1);
		assertEquals(updatedProduct.getStock(), produtToUpdate.getStock());
		
	}
	
	@Test
	void findById() {
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
	void deleteById() {
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
