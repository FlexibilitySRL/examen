package ar.com.plug.examen.domain.service.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(scripts = {"/schema.sql"})
public class ClientRepositoryTest
{
	@Autowired
	private ClientRepository clientRepository;
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
		assertThat(clientRepository).isNotNull();
	}

	@Test
	public void testSaveClient()
	{
		Client savedClient = clientRepository.save(client1);
		assertThat(savedClient.getId()).isGreaterThan(0);
		assertThat(savedClient.getActive()).isEqualTo(Boolean.TRUE);
		assertThat(savedClient.getName()).isEqualTo("TestClient1");
		assertThat(savedClient.getLastname()).isEqualTo("TestClient1Lastname");
		assertThat(savedClient.getPhone()).isEqualTo("+595971100100");
		assertThat(savedClient.getEmail()).isEqualTo("testclient1@email.com");
	}

	@Test
	public void testFindClientByDocument()
	{
		Client savedClient = clientRepository.save(client2);
		assertThat(savedClient).isNotNull();
		assertThat(savedClient.getDocument()).isEqualTo(clientRepository.findByDocument("564632").getDocument());
	}

	@Test
	public void testFindClientsPageable()
	{
		clientRepository.save(client1);
		clientRepository.save(client2);
		clientRepository.save(client3);
		clientRepository.save(client4);
		clientRepository.save(client5);

		Page<Client> page1 = clientRepository.findAll(PageRequest.of(0, 3));
		assertThat(page1).isNotNull();
		assertThat(page1.getSize()).isEqualTo(3);
		assertThat(page1.getContent().get(0).getDocument()).isEqualTo("564631");
		assertThat(page1.getContent().get(1).getDocument()).isEqualTo("564632");

		Page<Client> page2 = clientRepository.findAll(PageRequest.of(1, 3));
		assertThat(page2).isNotNull();
		assertThat(page2.getSize()).isEqualTo(3);
		assertThat(page2.getContent().get(0).getDocument()).isEqualTo("564634");
		assertThat(page2.getContent().get(1).getDocument()).isEqualTo("564635");
	}
}
