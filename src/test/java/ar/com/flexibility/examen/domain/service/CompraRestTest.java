package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.entities.*;
import ar.com.flexibility.examen.domain.service.CompraS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;



@RunWith(MockitoJUnitRunner.class)
public class CompraRestTest {

	@InjectMocks
	private CompraS cs;
	Producto prod = new Producto(1,"pc",5500.0f,"desktop");
	Cliente cli = new Cliente(1,"pedro","gallardo","a@a.com");
	Compra cmp = new Compra(1, false, cli, prod);
	
	@Test
	public void agregarCompraTest() {
		assertFalse(cs.agregar(cmp));
	}
	
	@Test
	public void borrarCompraTest() {
		assertFalse(cs.borrar(cmp.getId()));
	}
	
	@Test
	public void modificarCompraTest() {
		cmp.setAprobado(true);
		assertFalse(cs.modificar(cmp));
	}
	

}