package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.impl.ProcessClienteServiceImpl;
import ar.com.plug.examen.domain.service.impl.ProcessMessageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessMessageServiceTest {

	@InjectMocks
	private ProcessMessageServiceImpl messageService;
	@InjectMocks
	private ProcessClienteServiceImpl clienteService;

	@Test
	public void testProcessMessage() {
		String messageTest = "TEST";
		Message message = messageService.processMessage(messageTest);

		assertNotNull(message);
		assertEquals(message.getMessage(), messageTest);
	}

	@Test
	public Cliente testAddCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombres("Javier");
		cliente.setApellidos("Timote");
		cliente.setIdCliente(0);
		cliente.setDocumentoFact("123546890");
		cliente.setDireccion("cra");
		return this.clienteService.addCliente(cliente);
	}

}
