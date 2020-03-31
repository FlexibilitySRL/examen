package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.service.impl.ClienteServiceImpl;
import ar.com.genomasoft.jproject.core.exception.BaseException;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

	@InjectMocks
	private ClienteServiceImpl clienteservice;

	@Test
	public void creacionEntidad() throws BaseException {
		Cliente c = new Cliente();
		c.setCuit("20350831445");
		c.setDenominacion("La anonima");
		c.setCreatedByUser(1);
		
		Cliente c2 =clienteservice.save(c);
		 assertEquals(c, c2);
		 
		 editarEntidad(c2);
	}
	
	
	@Test
	public void editarEntidad(Cliente c2 ) throws BaseException {
		c2.setDenominacion("La anonima 2 ");
		
		Cliente c3 =clienteservice.update(c2);
		 assertEquals(c2, c3);
		 
		 eliminaEntidad(c3);
	}
	
	
	@Test
	public void eliminaEntidad(Cliente c3 ) throws BaseException {
			
		Cliente c4 =clienteservice.delete(c3);
		 assertEquals(c3, c4);
	}
	
		
}
