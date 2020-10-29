package ar.com.plug.examen.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

	@Test
	public void when_creatingCustomerWithoutParams_then_noExceptionsOccur() throws Exception {

		Assertions.assertDoesNotThrow(() -> {
			new Customer();
		}, "Excepcion ocurrida instanciando Customer");
	}

	@Test
	public void when_creatingCustomerWithParams_then_noExceptionsOccur() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			new Customer(1L, "firstName", "middleName", "lastName", "salutation", "cuit", "addressL1", "addressL2", "addressL3");
		}, "Excepcion ocurrida instanciando Customer");
	}

}
