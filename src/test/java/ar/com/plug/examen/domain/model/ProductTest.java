/**
 * 
 */
package ar.com.plug.examen.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author hellraiser
 *
 */
public class ProductTest {

	/**
	 * Test method for
	 * {@link ar.com.plug.examen.domain.model.Product#Product(Long, String, String, String)}.
	 */
	@Test
	public void when_creatingProductWithoutParams_then_noExceptionsOccur() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			new Product();
		}, "Excepcion ocurrida instanciando Product");
	}

	/**
	 * Test method for {@link ar.com.plug.examen.domain.model.Product#Product()}.
	 */
	@Test
	public void when_creatingProductWithParams_then_noExceptionsOccur() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			new Product(1L,"Coso", "Rojo", "http://...");
		}, "Excepcion ocurrida instanciando Product");
	}

}
