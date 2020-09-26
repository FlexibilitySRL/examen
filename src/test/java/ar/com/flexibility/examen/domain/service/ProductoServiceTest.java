package ar.com.flexibility.examen.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.flexibility.examen.domain.model.db.Product;
import ar.com.flexibility.examen.domain.model.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {
	@Mock
	@Autowired
	ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;

	@Test
	public void createProduct() {
		Product product = Product.builder().name("Producto 1").decription("Producto 1").cost(new BigDecimal("10000"))
				.salePrice(new BigDecimal("10000")).quantity(Long.valueOf(10)).provider("Nutresa").id(Long.valueOf("1"))
				.build();
		when(productRepository.saveAndFlush(Mockito.any(Product.class))).thenReturn(product);
		Product newProduct = productServiceImpl.createProduct(product);
		assertNotNull(newProduct.getId());
	}

	@Test
	public void updateProduct() {
		Optional<Product> product = Optional.of(Product.builder().name("Producto 1").decription("Producto 1")
				.cost(new BigDecimal("10000")).salePrice(new BigDecimal("10000")).quantity(Long.valueOf(10))
				.provider("Nutresa").id(Long.valueOf("1")).build());

		Mockito.when(productRepository.findById(Long.valueOf(1))).thenReturn(product);
		Mockito.when(productRepository.saveAndFlush(Mockito.any(Product.class))).thenReturn(product.get());
		Product newProduct = productServiceImpl.updateProduct(product.get());
		assertThat(newProduct.getId()).isSameAs(newProduct.getId());
	}

	@Test
	public void deleteClient() {
		Optional<Product> product = Optional.of(Product.builder().name("Producto 1").decription("Producto 1")
				.cost(new BigDecimal("10000")).salePrice(new BigDecimal("10000")).quantity(Long.valueOf(10))
				.provider("Nutresa").id(Long.valueOf("1")).build());
		Mockito.when(productRepository.findById(Long.valueOf(1))).thenReturn(product);
		productServiceImpl.deleteProduct("1");
	}
}
