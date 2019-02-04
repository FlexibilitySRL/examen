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
import ar.com.flexibility.examen.app.rest.ClienteController;
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.service.impl.ClienteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {
	
	@InjectMocks
	private ClienteController clienteController;
	@Mock
	private ClienteServiceImpl clientServices;
	
	@Before
	public void prepare() throws ServiceException, ValidationException {
		Cliente clienteUno = Mockito.mock(Cliente.class);
		Cliente clienteDos = Mockito.mock(Cliente.class);
		Cliente clienteTres = Mockito.mock(Cliente.class);
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(clienteUno);
		clientes.add(clienteDos);
		clientes.add(clienteTres);
		when(clientServices.findAll()).thenReturn(clientes);
	}

	/*
	 * Testing listar todos los clientes
	 */
	@Test
	public void getAllClientes() {
		System.out.println("TESTING getAllCliente");
		ArrayList<Cliente> list = clienteController.getAllCliente();
		assertEquals(list.size(), 3);
		System.out.println("FIN TESTING getAllCliente");
	}

	/*
	 * Testing alta de cliente
	 */
	@Test
	public void createCliente() {
			System.out.println("TESTING create");
			Cliente cliente = new Cliente ();
			cliente.setDni(37241273);
			cliente.setNombreYApellido("Octavio Etchechuri");
			cliente.setRazonSocial("Consumidor Final");
			ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) clienteController.createCliente(cliente);
			CustomResponse response = list.getBody();
			assertTrue(response.isSuccess());
			System.out.println("FIN TESTING create");
	}
	
	/*
	 * Testing actualizar un cliente
	 */
	@Test
	public void updateCliente() {
			System.out.println("TESTING update");
			Cliente cliente = new Cliente();
			cliente.setNombreYApellido("Cliente actualizado");
			cliente.setRazonSocial("Razon Social Actualizada");
			ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) clienteController.updateCliente(37241273, cliente);
			CustomResponse response = list.getBody();
			assertTrue(response.isError());
			System.out.println("FIN TESTING update");
	}
	
	/*
	 * Testing eliminar un cliente
	 */
	@Test
	public void removeCliente(){
		System.out.println("TESTING remove");
		Cliente cliente = Mockito.mock(Cliente.class);
		ResponseEntity<CustomResponse> list = (ResponseEntity<CustomResponse>) clienteController.deleteCliente(cliente.getDni());
		CustomResponse response = list.getBody();
		assertTrue(response.isError());
		System.out.println("FIN TESTING remove");
	}
	
}
