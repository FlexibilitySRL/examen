package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
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
		
		when(repo.save(any(Product.class))).then(AdditionalAnswers.returnsFirstArg());
		Product result = service.create(product);
		//verify(repo).save(product);
		
		assertEquals(result.getId(), product.getId());
	}

}
