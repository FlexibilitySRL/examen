package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.ValidatorsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class ClientServiceImplTest {

	@InjectMocks
	ClientServiceImpl clientService;
	
	@Mock
	ClientRepository clientRepository;

	@Mock
	ConverterService converter;

	@Mock
	ValidatorsService validators;

	private Client client;
	private ClientApi clientApi;
	
	@Before
	public void setUp() throws Exception {
		client = new Client(1L, "John Doe");
		clientApi = new ClientApi(1L, "John Doe");
	}
	
	@Test
	public void testListAll() throws NotFoundException {
		when(converter.convertList(clientRepository.findAll(), ClientApi.class)).thenReturn(Arrays.asList(clientApi));
		List<ClientApi> found = clientService.listAll();
		assertNotNull(found);
	}
	
	@Test
	public void testFindById() throws NotFoundException {
		when(converter.convert(clientRepository.findOneById(anyLong()), ClientApi.class)).thenReturn(clientApi);
		ClientApi found = clientService.findById(1L);
		assertNotNull(found);
		assertEquals(clientApi, found);
	}

	@Test
	public void testFindById_NotFoundException() throws NotFoundException {
		when(converter.convert(clientRepository.findOneById(anyLong()), ClientApi.class)).thenReturn(null);
		assertThrows(NotFoundException.class, () -> {
			clientService.findById(1L);
		});
	}

	@Test
	public void testFindByName() {
		when(converter.convertList(clientRepository.findByName(anyString()), ClientApi.class)).thenReturn(Arrays.asList(clientApi));
		List<ClientApi> clientApiList = clientService.findByName("mock name");
		assertNotNull(clientApiList);
		assertFalse(clientApiList.isEmpty());
		assertTrue(clientApiList.contains(clientApi));
	}

	@Test
	public void testSave() throws BadRequestException {
		ClientApi newClient = new ClientApi("test name");
		when(clientRepository.save(converter.convert(newClient, Client.class))).thenReturn(client);
		when(converter.convert(client, ClientApi.class)).thenReturn(clientApi);
		ClientApi saved = clientService.save(newClient);
		assertNotNull(saved);
		assertNull(newClient.getId());
	}

	@Test
	public void testSave_BadRequestException() throws BadRequestException {
		ClientApi newClient = new ClientApi();
		when(validators.checkCompleteObject(newClient, true)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			clientService.save(newClient);
		});
	}
	
	@Test
	public void testDeleteById() throws NotFoundException, BadRequestException {
		when(clientRepository.existsById(anyLong())).thenReturn(true);
		clientService.deleteById(1L);
	}

	@Test
	public void testDeleteById_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(client.getId(), false)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			clientService.deleteById(client.getId());
		});
	}

	@Test
	public void testDeleteById_NotFoundException() throws NotFoundException {
		when(clientRepository.existsById(client.getId())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> {
			clientService.deleteById(client.getId());
		});
	}

	@Test
	public void testUpdate() throws NotFoundException, BadRequestException {
		ClientApi newClient = new ClientApi(1L, "test name");
		when(clientRepository.existsById(newClient.getId())).thenReturn(true);
		when(clientRepository.save(converter.convert(newClient, Client.class))).thenReturn(client);
		when(converter.convert(client, ClientApi.class)).thenReturn(newClient);
		ClientApi saved = clientService.update(newClient);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assertEquals(client.getId(), saved.getId());
		assertNotEquals(client.getName(), saved.getName());
	}

	@Test
	public void testUpdate_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(clientApi, false)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			clientService.update(clientApi);
		});
	}

	@Test
	public void testUpdate_NotFoundException() throws NotFoundException {
		when(clientRepository.existsById(client.getId())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> {
			clientService.update(clientApi);
		});
	}
}