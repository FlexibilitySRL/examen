package ar.com.flexibility.examen.domain.service;

import org.junit.Test;
import org.mockito.InjectMocks;

import ar.com.flexibility.examen.domain.service.impl.ClienteServiceImpl;

public class TestClientes {
	
	@InjectMocks
	ClienteServiceImpl cliente;
	
	@Test
	public void proccesObtenerClientes() {
		System.out.println(this.cliente.obtenerClientes());
	}

}