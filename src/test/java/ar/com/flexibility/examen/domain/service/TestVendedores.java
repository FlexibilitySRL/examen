package ar.com.flexibility.examen.domain.service;

import org.junit.Test;
import org.mockito.InjectMocks;

import ar.com.flexibility.examen.domain.service.impl.VendedorServiceImpl;

public class TestVendedores {
	
	@InjectMocks
	VendedorServiceImpl vendedorImpl;
	
	@Test
	public void proccessVendedores() {
		System.out.println(this.vendedorImpl.obtenerVendedores());
	}

}
