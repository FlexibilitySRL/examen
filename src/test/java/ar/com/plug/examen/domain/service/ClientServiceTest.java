package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.domain.converter.ClientConverter;
import ar.com.plug.examen.domain.exception.ClientNotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest
{
	@InjectMocks
	private ClientServiceImpl clientService;

	@Mock
	private ClientRepository mockClientRepository;

	@Mock
	private ClientConverter mockClientConverter;

	@Test
	public void testGetClientById()
	{
		Client client = Client.builder().id(1L).build();
		when(mockClientRepository.findById(1L))
				.thenReturn(Optional.of(client));

		ClientDTO clientDTO = clientService.getClientById(1L);
		assertEquals(mockClientConverter.toDTO(client), clientDTO);
		verify(this.mockClientRepository, times(1)).findById(1L);
	}

	@Test
	public void testGetAllClients() {
		List<Client> clientList = Arrays.asList(Client.builder().id(1L).build(),
				Client.builder().id(2L).build());

		when(mockClientRepository.findAll())
				.thenReturn(clientList);

		List<ClientDTO> response = clientService.getAllClients();
		assertEquals(response.size(), 2);
	}

	@Test
	public void getByIdNotFoundTest() {
		when(mockClientRepository.findById(1L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ClientNotFoundException.class, () -> {
			clientService.getClientById(1L);
		});

		assertTrue(exception.getMessage().contains("Client with Id 1 not found"));
		verify(mockClientRepository, times(1)).findById(1L);
	}

	@Test
	public void saveSuccessTest() {
		ClientDTO expect = ClientDTO.builder().id(1L).build();
		Client client = Client.builder()
				.lastName("Boada")
				.firstName("Yeisson")
				.email("yeissonboada@gmail.com")
				.build();

		ClientDTO clientDTOSave = ClientDTO.builder()
				.lastName("Boada")
				.firstName("Yeisson")
				.email("yeissonboada@gmail.com")
				.build();

		when(mockClientConverter.toDTO(this.mockClientRepository.save(client))).thenReturn(expect);

		ClientDTO result = clientService.save(clientDTOSave);

		assertEquals(expect, result);

		verify(this.mockClientRepository, times(1))
				.save(client);
	}

	@Test
	public void updateTest() {
		ClientDTO expectResult = ClientDTO.builder().id(2L).build();
		Client client = Client.builder().id(2L).build();
		ClientDTO clientToSave = ClientDTO.builder().id(2L).build();
		when(mockClientConverter.toDTO(this.mockClientRepository.save(client))).thenReturn(expectResult);

		when(mockClientRepository.findById(2L)).thenReturn(Optional.of(client));

		ClientDTO result = this.clientService.update(clientToSave);
		assertEquals(expectResult, result);
		verify(mockClientRepository, times(1)).save(client);
	}


}
