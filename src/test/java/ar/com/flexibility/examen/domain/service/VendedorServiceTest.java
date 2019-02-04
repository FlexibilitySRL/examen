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
import ar.com.flexibility.examen.app.rest.VendedorController;
import ar.com.flexibility.examen.domain.model.Vendedor;
import ar.com.flexibility.examen.domain.service.impl.VendedorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class VendedorServiceTest {
	
	@InjectMocks
	private VendedorController vendedorController;
	@Mock
	private VendedorServiceImpl vendedorServices;
	
	@Before
	public void prepare() throws ServiceException, ValidationException {
		Vendedor vendedorUno = Mockito.mock(Vendedor.class);
		Vendedor vendedorDos = Mockito.mock(Vendedor.class);
		Vendedor vendedorTres = Mockito.mock(Vendedor.class);
		ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
		vendedores.add(vendedorUno);
		vendedores.add(vendedorDos);
		vendedores.add(vendedorTres);
		when(vendedorServices.findAll()).thenReturn(vendedores);
	}

	/*
	 * Testing listar todos los vendedores
	 */
	@Test
	public void getAllVendedores() {
		System.out.println("Test getAllVendedores");
		ArrayList<Vendedor> list = vendedorController.getAllVendedores();
		assertEquals(list.size(), 3);
		System.out.println("Fin de Test getAllVendedores");
	}

	/*
	 * Testing alta de vendedor
	 */
	@Test
	public void createVendedor() {
			System.out.println("Test create vendedor");
			Vendedor vendedor = new Vendedor();
			vendedor.setIdVendedor(1);
			ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) vendedorController.createVendedor(vendedor);
			CustomResponse response = list.getBody();
			assertTrue(response.isSuccess());
			System.out.println("Fin de Test create vendedor");
	}
	
	/*
	 * Testing actualizar un vendedor
	 */
	@Test
	public void updateVendedor() {
			System.out.println("Test update vendedor");
			Vendedor vendedor = new Vendedor();
			vendedor.setIdVendedor(2);
			ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) vendedorController.updateVendedor(1, vendedor);
			CustomResponse response = list.getBody();
			assertTrue(response.isError());
			System.out.println("Fin de Test update vendedor");
	}
	
	/*
	 * Testing eliminar un vendedor
	 */
	@Test
	public void removeVendedor(){
		System.out.println("Test remove vendedor");
		Vendedor vendedor = Mockito.mock(Vendedor.class);
		ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) vendedorController.deleteVendedor(vendedor.getIdVendedor());
		CustomResponse response = list.getBody();
		assertTrue(response.isError());
		System.out.println("Fin de Test remove vendedor");
	}
	
}
