package ar.com.flexibility.examen.domain.service;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
import ar.com.flexibility.examen.app.rest.ComprobanteController;
import ar.com.flexibility.examen.domain.model.*;
import ar.com.flexibility.examen.domain.service.impl.ComprobanteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ComprobanteServiceTest {
	
	@InjectMocks
	private ComprobanteController comprobanteController;
	@Mock
	private ComprobanteServiceImpl comprobanteServices;
	
	@Before
	public void prepare() throws ServiceException, ValidationException {
		Comprobante comprobanteUno = Mockito.mock(Comprobante.class);
		Comprobante comprobanteDos = Mockito.mock(Comprobante.class);
		Comprobante comprobanteTres = Mockito.mock(Comprobante.class);
		ArrayList<Comprobante> productos = new ArrayList<Comprobante>();
		productos.add(comprobanteUno);
		productos.add(comprobanteDos);
		productos.add(comprobanteTres);
		when(comprobanteServices.findAll()).thenReturn(productos);
	}

	/*
	 * Testing listar todos los comprobantes
	 */
	@Test
	public void getAllComprobantes() {
		System.out.println("Test getAllComprobantes");
		ArrayList<Comprobante> list = comprobanteController.getAllComprobantes();
		assertEquals(list.size(), 3);
		System.out.println("Fin de Test getAllComprobantes");
	}

	/*
	 * Testing alta de comprobante
	 */
	@Test
	public void createComprobante() {
			System.out.println("Test create comprobante");
			Cliente cliente = Mockito.mock(Cliente.class);
			Renglon renglon = Mockito.mock(Renglon.class);
			Set<Renglon> renglones = new HashSet<Renglon> (); 
			renglones.add(renglon);
			Vendedor vendedor = Mockito.mock(Vendedor.class);
			Comprobante comprobante = new Comprobante();
			comprobante.setCliente(cliente);
			comprobante.setAutorizado(false);
			comprobante.setEmpleado(null);
			comprobante.setIdComprobante(1);
			comprobante.setRenglon(renglones);
			comprobante.setVendedor(vendedor);
			comprobante.setFechaAutorizacion(null);
			ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) comprobanteController.createComprobante(comprobante);
			CustomResponse response = list.getBody();
			assertTrue(response.isSuccess());
			System.out.println("Fin de Test create comprobante");
	}
	
	/*
	 * Testing actualizar un comprobante
	 */
	@Test
	public void updateComprobante() {
			System.out.println("Test update comprobante");
			Cliente cliente = Mockito.mock(Cliente.class);
			Renglon renglon = Mockito.mock(Renglon.class);
			Set<Renglon> renglones = new HashSet<Renglon> (); 
			renglones.add(renglon);
			Vendedor vendedor = Mockito.mock(Vendedor.class);
			Comprobante comprobante = new Comprobante();
			comprobante.setCliente(cliente);
			comprobante.setAutorizado(false);
			comprobante.setEmpleado(null);
			comprobante.setRenglon(renglones);
			comprobante.setVendedor(vendedor);
			comprobante.setFechaAutorizacion(null);
			ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) comprobanteController.updateComprobante(1, comprobante);
			CustomResponse response = list.getBody();
			assertTrue(response.isError());
			System.out.println("Fin de Test update comprobante");
	}
	
	/*
	 * Testing eliminar un comprobante
	 */
	@Test
	public void removeComprobante(){
		System.out.println("Test remove comprobante");
		Comprobante comprobante = Mockito.mock(Comprobante.class);
		ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) comprobanteController.deleteComprobante(comprobante.getIdComprobante());
		CustomResponse response = list.getBody();
		assertTrue(response.isError());
		System.out.println("Fin de Test remove comprobante");
	}
	
	/*
	 * Testing validar un comprobante
	 */
	@Test
	public void validarComprobante(){
		System.out.println("Test validar comprobante");
		Comprobante comprobante = Mockito.mock(Comprobante.class);
		ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) comprobanteController.validarVendedor(1, comprobante);
		CustomResponse response = list.getBody();
		assertTrue(response.isError());
		System.out.println("Fin de Test validar comprobante");
	}
	
}
