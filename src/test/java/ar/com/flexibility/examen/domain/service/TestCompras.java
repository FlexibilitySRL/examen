package ar.com.flexibility.examen.domain.service;

import org.junit.Test;
import org.mockito.InjectMocks;

import ar.com.flexibility.examen.domain.service.impl.CompraServiceImpl;

public class TestCompras {
	
	@InjectMocks
	CompraServiceImpl compraImpl;
	
	@Test
	public void processCompras() {
		System.out.println(this.compraImpl.obtenerCompras());
	}

}
