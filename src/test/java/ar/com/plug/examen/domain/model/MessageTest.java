package ar.com.plug.examen.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageTest {

	@Test
	public final void testMessage() throws Exception {
		Assertions.assertDoesNotThrow(() -> {
			new Message("hola");
		}, "Excepcion ocurrida instanciando Message");
	}


}
