/**
 * 
 */
package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.plug.examen.domain.model.Product;

/**
 * @author hellraiser
 *
 */
@SpringBootTest
public class ProductServiceImplTest {

	@Autowired
	ProductServiceImpl productService;

	/**
	 * Test method for
	 * {@link ar.com.plug.examen.domain.service.impl.ProductServiceImpl#createProduct(ar.com.plug.examen.domain.model.Product)}.
	 */
	@Test
	public void when_creatingNewProduct_then_savedParametersMatch() throws Exception {

		Product product = new Product();
		product.setColor("negro");
		product.setName("Televisor");
		product.setThumbnail("http://...");

		Product product2 = productService.createProduct(product);
		assertEquals("El color debe coincidir", product.getColor(), product2.getColor());
		assertEquals("El nombre debe coincidir", product.getName(), product2.getName());
		assertEquals("El thumbnail debe coincidir", product.getThumbnail(), product2.getThumbnail());

	}

}
