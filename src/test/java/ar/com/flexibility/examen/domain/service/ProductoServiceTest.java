package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.impl.ProductoServiceImpl;
import ar.com.genomasoft.jproject.core.exception.BaseException;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {

	@InjectMocks
	private ProductoServiceImpl productoService;

	@Test
	public void creacionEntidad() throws BaseException {
		Producto p = new Producto();
		p.setDescripcion("Bananas amarillas");
		p.setPrecio(20.0);
		p.setCreatedByUser(1);
			
		Producto p2 =productoService.save(p);
		 assertEquals(p, p2);
		 
		 editarEntidad(p2);
	}
	
	
	@Test
	public void editarEntidad(Producto p2 ) throws BaseException {
		
		p2.setDescripcion("Bananas verdes");
		
		Producto p3 =productoService.update(p2);
		
		 assertEquals(p2, p3);
		 
		 eliminaEntidad(p3);
	}
	
	
	@Test
	public void eliminaEntidad(Producto p3 ) throws BaseException {
			
		Producto p4 =productoService.delete(p3);
		 assertEquals(p3, p4);
	}
	
		
}
