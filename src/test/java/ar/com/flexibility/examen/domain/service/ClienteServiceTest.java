package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.impl.ClienteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

	@InjectMocks
	private ClienteServiceImpl clienteService;
	
	@Before
	public void setUp() throws Exception {
	
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente(1, "cliente 1", "100"));
		clientes.add(new Cliente(2, "cliente 2", "200"));

		clienteService.setClientes(clientes);
	}
	
	@Test
	public void testFindByCienteExiste() {
		Cliente c = clienteService.findById(1);
		assertNotNull(c);
		assertEquals(1, c.getId());
		assertEquals("cliente 1", c.getNombre());
		assertEquals("100", c.getTarjeta());
	}

	@Test
	public void testUpdate() {
		Cliente c = new Cliente(2, "cliente 3", "300");
		clienteService.update(c);
		assertEquals("cliente 3", clienteService.findById(2).getNombre());
		assertEquals("300", clienteService.findById(2).getTarjeta());
		
	}

	@Test
	public void testDeleteCliente() {
		clienteService.deleteById(2);
		assertNull(clienteService.findById(2));
	}

	@Test
	public void testSaveCrearNuevoCliente() {
		Cliente c = new Cliente("Cliente Nuevo", "500");
		Cliente nuevo = clienteService.save(c);
		assertEquals("Cliente Nuevo", nuevo.getNombre());
		
		assertEquals(nuevo.getId(), clienteService.findById(nuevo.getId()).getId());
	}

}
