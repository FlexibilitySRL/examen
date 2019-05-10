package ar.com.flexibility.examen.app.api;

import java.math.BigDecimal;

import org.junit.Test;

import ar.com.flexibility.examen.domain.model.Product;

public class ProductApiTest
{

	@Test
	public void testProductApiEquals()
	{
		//given
		ProductApi pApi1 = null;
		ProductApi pApi2 = null;
		ProductApi pApi3 = null;
		ProductApi pApi4 = null;
		ProductApi pApi5 = null;
		Product product = new Product();
		product.setId(3L);
		product.setDescription("description product api");
		product.setPrice(new BigDecimal(100));
		
		//when
		pApi1 = new ProductApi();
		pApi2 = pApi1;
		pApi3 = new ProductApi(product);
		pApi4 = new ProductApi();
		pApi4.setId(product.getId());
		pApi4.setDescription(product.getDescription());
		pApi4.setPrice(product.getPrice());
		pApi5 = new ProductApi(product);
		pApi5.setId(null);
		
		//then
		assert(!pApi1.equals(null));
		
		assert(pApi1.equals(pApi2));
		assert(pApi1.hashCode() == pApi2.hashCode());
		
		assert(!pApi3.equals(product));
		assert(pApi3.hashCode() == product.hashCode());
		
		assert(pApi3.equals(new ProductApi(product)));
		assert(pApi3.hashCode() == new ProductApi(product).hashCode());
		
		assert(pApi4.equals(new ProductApi(product)));
		assert(pApi4.hashCode() == new ProductApi(product).hashCode());
		
		assert(pApi3.equals(pApi4));
		assert(pApi3.hashCode() == pApi4.hashCode());
		
		assert(!pApi5.equals(new ProductApi(product)));
		assert(pApi5.hashCode() != new ProductApi(product).hashCode());
	}
}
