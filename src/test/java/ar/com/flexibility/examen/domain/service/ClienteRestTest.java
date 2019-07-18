package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.entities.Cliente;
import ar.com.flexibility.examen.domain.service.ClienteS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClienteRestTest {

	@InjectMocks
	private ClienteS cs;
	private Cliente cli = new Cliente(1,"pedro","gallardo","a@a.com");
	
	@Test
	public void agregarClienteTest() {
		assertFalse(cs.agregar(cli));
	}
	
	@Test
	public void borrarClienteTest() {
		assertFalse(cs.borrar(cli.getId()));
	}
	
	@Test
	public void modificarClienteTest() {
		cli.setApellido("Gomez");
		assertFalse(cs.modificar(cli));
	}
	

}
