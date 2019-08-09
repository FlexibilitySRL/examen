package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.impl.ProductoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {

	@InjectMocks
	private ProductoServiceImpl productoService;
	
	@Before
	public void setUp() throws Exception {
	
		List<Producto> productos = new ArrayList<Producto>();
		productos.add(new Producto(1, "producto X"));
		productos.add(new Producto(2, "producto Y"));

		productoService.setProductos(productos);
	}
	
	@Test
	public void testFindByProductoNoExiste() {
		Producto p = productoService.findById(100);
		assertNull(p);
	}
	
	@Test
	public void testFindByProductoExiste() {
		Producto p = productoService.findById(1);
		assertNotNull(p);
		assertEquals(1, p.getId());
		assertEquals("producto X", p.getNombre());
	}

	@Test
	public void testUpdate() {
		Producto p = new Producto(2, "Producto C");
		productoService.update(p);
		assertEquals("Producto C", productoService.findById(2).getNombre());
		
	}

	@Test
	public void testDeleteProducto() {
		productoService.deleteById(2);
		assertNull(productoService.findById(2));
	}

	@Test
	public void testSaveCrearNuevoProducto() {
		Producto p = new Producto("Producto Nuevo");
		Producto nuevo = productoService.save(p);
		assertEquals("Producto Nuevo", nuevo.getNombre());
		
		assertEquals(nuevo.getId(), productoService.findById(nuevo.getId()).getId());
	}

}
