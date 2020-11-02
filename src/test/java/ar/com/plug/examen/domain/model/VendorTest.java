package ar.com.plug.examen.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VendorTest {

	/**
	 * Test method for
	 * {@link ar.com.plug.examen.domain.model.Vendor#Vendor(Long, String, String, String)}.
	 */
	@Test
	public void when_creatingVendorWithoutParams_then_noExceptionsOccur() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			new Vendor();
		}, "Excepcion ocurrida instanciando Vendor");
	}

	/**
	 * Test method for {@link ar.com.plug.examen.domain.model.Vendor#Vendor()}.
	 */
	@Test
	public void when_creatingVendorWithParams_then_noExceptionsOccur() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			new Vendor(1L, "nombre", "segundo nombre", "apellido", "username");
		}, "Excepcion ocurrida instanciando Vendor");
	}

}
