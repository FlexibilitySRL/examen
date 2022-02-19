package ar.com.plug.examen.domain.service.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.ClientDto;
import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest
{
	@InjectMocks
	private ClientServiceImpl service;

	@Mock
	private ClientRepository repository;

	private Client client1;
	private Client client2;
	private Client client3;
	private Client client4;
	private Client client5;
	private Client client6;

	@Before
	public void setup()
	{
		client1 = Client.builder()
			.name("TestClient1")
			.lastname("TestClient1Lastname")
			.document("564631")
			.phone("+595971100100")
			.email("testclient1@email.com")
			.active(Boolean.TRUE)
			.build();

		client2 = Client.builder()
			.name("TestClient2")
			.lastname("TestClient2Lastname")
			.document("564632")
			.phone("+595971100102")
			.email("testclient2@email.com")
			.active(Boolean.TRUE)
			.build();

		client3 = Client.builder()
			.name("TestClient3")
			.lastname("TestClient3Lastname")
			.document("564633")
			.phone("+595971100103")
			.email("testclient3@email.com")
			.active(Boolean.TRUE)
			.build();

		client4 = Client.builder()
			.name("TestClient4")
			.lastname("TestClient4Lastname")
			.document("564634")
			.phone("+595971100104")
			.email("testclient4@email.com")
			.active(Boolean.TRUE)
			.build();

		client5 = Client.builder()
			.name("TestClient5")
			.lastname("TestClient5Lastname")
			.document("564635")
			.phone("+595971100105")
			.email("testclient5@email.com")
			.active(Boolean.TRUE)
			.build();

		client6 = Client.builder()
			.name("TestClient6")
			.lastname("TestClient6Lastname")
			.document("564636")
			.phone("+595971100106")
			.email("testclient6@email.com")
			.active(Boolean.TRUE)
			.build();
	}

	@Test
	public void autowiredNotNull()
	{
		assertThat(repository).isNotNull();
	}

	@Test
	public void getClientByIdTest()
	{
		client1.setId(10L);
		when(repository.findById(10L)).thenReturn(Optional.ofNullable(client1));
		Client clientFromService = service.getClientById(10L);
		assertThat(clientFromService.getId()).isEqualTo(client1.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getClientByIdNullThrowsNoSuchElementExceptionTest()
	{
		service.getClientById(null);
	}

	@Test
	public void getClientByDocumentTest()
	{
		when(repository.findByDocument("564632")).thenReturn(client2);
		Client clientFromService = service.getClientByDocumentNumber("564632");
		assertThat(clientFromService.getId()).isEqualTo(client2.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getClientDocumentIdNullThrowsNoSuchElementExceptionTest()
	{
		service.getClientByDocumentNumber(null);
	}

	@Test
	public void getAllClientsPaginated()
	{
		int pageSize = 2;
		int pageNumber = 0;
		List<Client> page1Clients = Arrays.asList(client1, client2);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Client> clientPage = new PageImpl<>(page1Clients);
		when(repository.findAll(pageable)).thenReturn(clientPage);

		PageDto<Client> clientPageTest = service.getAllClients(pageNumber, pageSize);
		assertThat(clientPageTest.getContent().size()).isEqualTo(pageSize);
		assertThat(clientPageTest.getContent().get(0).getDocument()).isEqualTo(client1.getDocument());
		assertThat(clientPageTest.getContent().get(1).getDocument()).isEqualTo(client2.getDocument());
	}

	@Test(expected = ValidationException.class)
	public void saveClientThrowsExceptionTest() throws ValidationException
	{
		service.saveClient(null);
	}

	@Test
	public void saveClientTest() throws ValidationException
	{
		client1.setId(1L);
		ClientDto dto = new ClientDto(client1.getName(), client1.getLastname(),
			client1.getDocument(), client1.getPhone(), client1.getEmail(), client1.getActive());
		when(repository.save(any(Client.class))).thenReturn(client1);
		Client savedClient = service.saveClient(dto);
		assertThat(savedClient).isNotNull();
		assertThat(savedClient.getDocument()).isEqualTo(dto.getDocument());
	}

	@Test
	public void updateClientTest() throws ValidationException
	{
		String updatedName = "updated";
		client1.setId(1L);
		client1.setName(updatedName);

		ClientDto dto = new ClientDto(updatedName, client1.getLastname(),
			client1.getDocument(), client1.getPhone(), client1.getEmail(), client1.getActive());
		when(repository.existsById(1L)).thenReturn(true);
		when(repository.save(any(Client.class))).thenReturn(client1);

		Client updatedClient = service.updateClient(1L, dto);
		assertThat(updatedClient).isNotNull();
		assertThat(updatedClient.getDocument()).isEqualTo(dto.getDocument());
		assertThat(updatedClient.getName()).isEqualTo(updatedName);
	}

	@Test(expected = NoSuchElementException.class)
	public void updateClientWrongId() throws ValidationException
	{
		String updatedName = "updated";
		client1.setId(1L);
		client1.setName(updatedName);

		ClientDto dto = new ClientDto(updatedName, client1.getLastname(),
			client1.getDocument(), client1.getPhone(), client1.getEmail(), client1.getActive());
		when(repository.existsById(1L)).thenReturn(false);
		when(repository.save(any(Client.class))).thenReturn(client1);
		service.updateClient(1L, dto);
	}

	@Test(expected = ValidationException.class)
	public void updateClientNullId() throws ValidationException
	{
		String updatedName = "updated";
		client1.setId(1L);
		client1.setName(updatedName);

		ClientDto dto = new ClientDto(updatedName, client1.getLastname(),
			client1.getDocument(), client1.getPhone(), client1.getEmail(), client1.getActive());
		when(repository.existsById(1L)).thenReturn(false);
		when(repository.save(any(Client.class))).thenReturn(client1);
		service.updateClient(null, dto);
	}

	@Test(expected = ValidationException.class)
	public void updateClientNullDto() throws ValidationException
	{
		String updatedName = "updated";
		client1.setId(1L);
		client1.setName(updatedName);

		ClientDto dto = new ClientDto(updatedName, client1.getLastname(),
			client1.getDocument(), client1.getPhone(), client1.getEmail(), client1.getActive());
		when(repository.existsById(1L)).thenReturn(false);
		when(repository.save(any(Client.class))).thenReturn(client1);
		service.updateClient(1L, null);
	}

	@Test(expected = ValidationException.class)
	public void inactiveClientNullId() throws ValidationException
	{
		service.inactivateClient(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void inactivateClientWrongId() throws ValidationException
	{
		when(repository.existsById(1L)).thenReturn(false);
		service.inactivateClient(1L);
	}

	@Test
	public void inactivateClientTest() throws ValidationException
	{
		client1.setId(1L);
		client1.setActive(false);
		when(repository.existsById(1L)).thenReturn(true);
		when(repository.getOne(1L)).thenReturn(client1);
		when(repository.save(any(Client.class))).thenReturn(client1);
		Client inactiveClient = service.inactivateClient(1L);
		assertThat(inactiveClient).isNotNull();
		assertThat(inactiveClient.getActive()).isFalse();
		assertThat(inactiveClient.getId()).isEqualTo(client1.getId());
	}
}
