package ar.com.plug.examen.app.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.com.plug.examen.domain.dto.ClienteDTO;
import ar.com.plug.examen.domain.service.ClienteService;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTests {

	@Mock
	private ClienteService clienteService;

	@InjectMocks
	private ClienteController clienteController;

	@Test
	public void testCrearCliente() 
	{
		
			ClienteDTO clienteParaGuardar = new ClienteDTO(0L, "Maria","Mendoza","Av. Los Arboles","1596-58963","maria@correo.com","V1600000");
			ClienteDTO clienteGuardado = new ClienteDTO(1L, "Maria","Mendoza","Av. Los Arboles","1596-58963","maria@correo.com","V1600000");
			when(this.clienteService.crearCliente(clienteParaGuardar)).thenReturn(clienteGuardado);
			
			ResponseEntity<ClienteDTO> response = clienteController.crearCliente(clienteParaGuardar);
	        assertNotNull(response);
	        assertNotNull(response.getBody());
	        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
	        assertThat(response.getBody().getNumeroIdentificacion(), is("V1600000"));
	        assertThat(response.getBody().getNombre(), is("Maria"));
	        assertThat(response.getBody().getApellido(), is("Mendoza"));
	        assertThat(response.getBody().getDireccion(), is("Av. Los Arboles"));
	        assertThat(response.getBody().getTelefono(), is("1596-58963"));
	        assertThat(response.getBody().getEmail(), is("maria@correo.com"));
	        verify(clienteService).crearCliente(clienteParaGuardar);
	}	
}
