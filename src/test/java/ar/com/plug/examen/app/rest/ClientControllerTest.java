package ar.com.plug.examen.app.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import java.util.*;
import ar.com.plug.examen.domain.validators.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

	@InjectMocks
	private ClientController clientController;

	@Mock
	private ClientServiceImpl mockClientService;

	@Mock
	private Validator mockValidator;

	@Test
	public void testSaveClient() {
		ClientDTO clientDTO = ClientDTO.builder().id(1L).build();

		ResponseEntity<ClientDTO> savedClient = clientController.save(clientDTO);

		assertEquals(HttpStatus.CREATED, savedClient.getStatusCode());
		verify(mockClientService, times(1)).save(clientDTO);
	}

	@Test
	public void testGetAllClients() {
		List<ClientDTO> clientDTOList = Arrays.asList(ClientDTO.builder().id(1L).build(), ClientDTO.builder().id(2L).build());

		when(mockClientService.getAllClients()).thenReturn(clientDTOList);

		ResponseEntity<List<ClientDTO>> allClients = clientController.getAllClients();

		assertEquals(HttpStatus.OK, allClients.getStatusCode());

		assertEquals(2, allClients.getBody().size());
		verify(mockClientService, times(1)).getAllClients();
	}

	@Test
	public void testGetClientById() {

		ClientDTO clientDTO = ClientDTO.builder().id(1L).build();
		when(mockClientService.getClientById(1L)).thenReturn(clientDTO);
		ResponseEntity<ClientDTO> result = clientController.getClientById(1L);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(clientDTO, result.getBody());
		verify(mockClientService, times(1))
				.getClientById(1L);
	}

	@Test
	public void testDeleteClient() {

		ResponseEntity<?> delete = clientController.delete(1L);

		assertEquals(HttpStatus.OK, delete.getStatusCode());
		verify(this.mockClientService, times(1)).delete(1L);
	}

}
