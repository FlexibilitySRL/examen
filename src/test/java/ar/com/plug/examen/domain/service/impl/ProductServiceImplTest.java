package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private ProductRepository productRepository;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		final Product objectToCreate = new Product("ensalada", "con sesamo",100);
		final Product objectCreated = new Product(1L, "ensalada", "con sesamo",100);

		when(productRepository.save(objectToCreate)).thenReturn(objectCreated);

		Product result = productService.create(objectToCreate);
		assertEquals(objectCreated.getId(), result.getId());
	}

	
	@Test
	public void update() {
		final Product objectToUpdate = new Product(3L,"pizza", "Roque ",200);
		final Product objectUpdated = new Product(3L, "pizza", "jamon ",200);

		when(productRepository.save(objectToUpdate)).thenReturn(objectUpdated);

		Product result = productService.update(2L,objectToUpdate);

		assertNotEquals(result.getName(), objectToUpdate.getDescription());

	}
	
	
	@Test
	public void findById() {
		final Product product = new Product(1L, "coca", "cola",75);

		Optional<Product> objectCreated = Optional.of(product);

		when(productRepository.findById(product.getId())).thenReturn(
				objectCreated);

		Optional<Product> result = productService.getProductById(1L);

		assertEquals(objectCreated, result);

	}

	@Test
	public void findAll() {
		List<Product> objectCreated = new ArrayList<>();

		objectCreated.add(new Product(1L, "helado 1 KG ", "sabores",200));
		objectCreated.add(new Product(2L, "torta ", "ricota", 125));

		when(productRepository.findAll()).thenReturn(objectCreated);

		List<Product> result = productService.getProducts();

		assertEquals(2, result.size());

	}

 
}
