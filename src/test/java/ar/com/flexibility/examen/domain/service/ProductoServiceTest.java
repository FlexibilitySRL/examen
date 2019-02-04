package ar.com.flexibility.examen.domain.service;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import javax.validation.ValidationException;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import ar.com.flexibility.examen.app.api.CustomResponse;
import ar.com.flexibility.examen.app.rest.ProductoController;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.impl.ProductoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {
	
	@InjectMocks
	private ProductoController productoController;
	@Mock
	private ProductoServiceImpl productoServices;
	
	@Before
	public void prepare() throws ServiceException, ValidationException {
		Producto productoUno = Mockito.mock(Producto.class);
		Producto productoDos = Mockito.mock(Producto.class);
		Producto productoTres = Mockito.mock(Producto.class);
		ArrayList<Producto> productos = new ArrayList<Producto>();
		productos.add(productoUno);
		productos.add(productoDos);
		productos.add(productoTres);
		when(productoServices.findAll()).thenReturn(productos);
	}

	/*
	 * Testing listar todos los producto
	 */
	@Test
	public void getAllProducto() {
		System.out.println("Test getAllProducto");
		ArrayList<Producto> list = productoController.getAllProductos();
		assertEquals(list.size(), 3);
		System.out.println("Fin de Test getAllProducto");
	}

	/*
	 * Testing alta de producto
	 */
	@Test
	public void createProducto() {
			System.out.println("Test create producto");
			Producto producto = new Producto();
			producto.setIdProducto(1);
			producto.setNombre("Producto test");
			producto.setMarca("Marca test");
			producto.setFechaVencimiento(Long.valueOf((1548982912)));
			ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) productoController.createProducto(producto);
			CustomResponse response = list.getBody();
			assertTrue(response.isSuccess());
			System.out.println("Fin de Test create producto");
	}
	
	/*
	 * Testing actualizar un producto
	 */
	@Test
	public void updateProducto() {
			System.out.println("Test update producto");
			Producto producto = new Producto();
			producto.setNombre("Update test");
			producto.setMarca("Update test");
			producto.setFechaVencimiento(Long.valueOf((1548983045)));
			ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) productoController.updateProducto(1, producto);
			CustomResponse response = list.getBody();
			assertTrue(response.isError());
			System.out.println("Fin de Test update producto");
	}
	
	/*
	 * Testing eliminar un producto
	 */
	@Test
	public void removeProducto(){
		System.out.println("Test remove producto");
		Producto producto = Mockito.mock(Producto.class);
		ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) productoController.deleteProducto(producto.getIdProducto());
		CustomResponse response = list.getBody();
		assertTrue(response.isError());
		System.out.println("Fin de Test remove producto");
	}
	
}
