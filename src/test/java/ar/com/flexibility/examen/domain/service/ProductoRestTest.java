package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.entities.Producto;
import ar.com.flexibility.examen.domain.service.ProductoS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;



@RunWith(MockitoJUnitRunner.class)
public class ProductoRestTest {

	@InjectMocks
	private ProductoS ps;
	private Producto prod = new Producto(1,"pc",5500.0f,"desktop");
	
	@Test
	public void agregarProductoTest() {
		assertFalse(ps.agregar(prod));
	}
	
	@Test
	public void borrarProductoTest() {
		assertFalse(ps.borrar(prod.getId()));
	}
	
	@Test
	public void modificarProductoTest() {
		prod.setCategoria("computacion");
		assertFalse(ps.modificar(prod));
	}
	

}