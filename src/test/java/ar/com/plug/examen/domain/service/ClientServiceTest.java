package ar.com.plug.examen.domain.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientServiceTest {

	@Mock
	private ClientRepository repository;

	@InjectMocks
	private ClientServiceImpl service;
	
	static final Client client = new Client();
	
	static {
		client.setId(123L);
		client.setIdentification("12345678");
		client.setNames("test names");
		client.setSurnames("test surnames");
	}

	@Test
	public void testSave() {

		when(repository.save(any())).thenReturn(client);

		Client newClient = service.save(client);

		assertNotNull(newClient);
		assertNotNull(newClient.getId());
	}
	
	@Test
	public void testSaveBadResource() {
		when(repository.save(any())).thenThrow(new RuntimeException());
		assertThatExceptionOfType(BadResourceException.class).isThrownBy(() -> service.save(client));
	}
	
	@Test
	public void testUpdate() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(client));
		when(repository.save(any())).thenReturn(client);

		Client updateClient = service.update(client);

		assertNotNull(updateClient);
		assertNotNull(updateClient.getId());
	}
	
	@Test
	public void testFindById() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(client));

		Client updateClient = service.findById(123L);

		assertNotNull(updateClient);
		assertNotNull(updateClient.getId());
	}
	
	@Test
	public void testFindAll() {
		Page<Client> page = mock(Page.class);
		when(repository.findAll(any(Pageable.class))).thenReturn(page);

		Page<Client> list = service.findAll(1, 2000);

		assertNotNull(list);
	}
	
	
	@Test
	public void testDelete() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(client));
		service.deleteById(123L);
	}

	@Test
	public void testFindByIdBadRequest() {		
		assertThatExceptionOfType(BadResourceException.class).isThrownBy(() -> service.findById(null));
	}
	
	@Test
	public void testFindByIdNotFound() {
		when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> service.findById(123L));
	}


}
