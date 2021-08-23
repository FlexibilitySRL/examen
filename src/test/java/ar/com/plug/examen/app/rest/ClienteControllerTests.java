package ar.com.plug.examen.app.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ar.com.plug.examen.data.entity.Cliente;
import ar.com.plug.examen.data.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTests {

	@InjectMocks
	ClienteController clienteController;

	@Mock
	ClienteRepository clienteRepository;

	@Test
	public void testCrearCliente() 
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Cliente cliente = new Cliente();
		cliente.setIdCliente(1L);

		when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

		Cliente clienteNuevo = new Cliente(0, "V1600000", "Maria","Mendoza","Av. Los Arboles","maria@correo.com","1596-58963", null ,null); 
		ResponseEntity<Cliente> responseEntity = clienteController.crearCliente(clienteNuevo);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
	}

	@Test
	public void testFindAll() 
	{
		Cliente cliente1 = new Cliente(0, "V2500000", "Jose","Mendoza","Av. Los Arboles","jose@correo.com","0123-5963", null ,null); 
		Cliente cliente2 = new Cliente(0, "V3000000", "Sofia","Mendoza","Av. Los Arboles","sofia@correo.com","9854-2563", null ,null); 
		List<Cliente> list = new ArrayList<Cliente>();
		list.addAll(Arrays.asList(cliente1, cliente2));

		when(clienteRepository.findAll()).thenReturn(list);

		// when
		ResponseEntity<Iterable<Cliente>> result = clienteController.listarClientes();
		System.out.println(result.getBody());
		assertThat(result.getStatusCodeValue()).isEqualTo(200);


	}
}
