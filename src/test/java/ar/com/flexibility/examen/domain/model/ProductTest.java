package ar.com.flexibility.examen.domain.model;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.app.api.ProductApi;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest
{
	
	@Test
	public void testProductEquals()
	{
		//given
		Product p1 = null;
		Product p2 = null;
		Product p3 = null;
		Product p4 = null;
		Product p5 = null;
		ProductApi pApi = new ProductApi();
		pApi.setId(3L);
		pApi.setDescription("full name product");
		pApi.setPrice(new BigDecimal(100));
		
		//when
		p1 = new Product();
		p2 = p1;
		p3 = new Product(pApi);
		p4 = new Product();
		p4.setId(pApi.getId());
		p4.setDescription(pApi.getDescription());
		p4.setPrice(pApi.getPrice());
		p5 = new Product(pApi);
		p5.setId(null);
		
		//then
		assert(!p1.equals(null));
		
		assert(p1.equals(p2));
		assert(p1.hashCode() == p2.hashCode());
		
		assert(!p3.equals(pApi));
		assert(p3.hashCode() == pApi.hashCode());
		
		assert(p3.equals(new Product(pApi)));
		assert(p3.hashCode() == new Product(pApi).hashCode());
		
		assert(p4.equals(new Product(pApi)));
		assert(p4.hashCode() == new Product(pApi).hashCode());
		
		assert(p3.equals(p4));
		assert(p3.hashCode() == p4.hashCode());
		
		assert(!p5.equals(new Product(pApi)));
		assert(p5.hashCode() != new Product(pApi).hashCode());
	}

}
