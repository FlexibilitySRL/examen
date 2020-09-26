package ar.com.flexibility.examen.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.flexibility.examen.domain.model.db.Client;
import ar.com.flexibility.examen.domain.model.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {
	@Mock
	@Autowired
	ClientRepository clientRepository;

	@InjectMocks
	private ClientServiceImpl clientServiceImpl;

	@Test
	public void createClient() {
		Client client = Client.builder().documentNumber("1").documentType("1").name("Juan").lastName("Pena").sex("M")
				.age("15").build();
		when(clientRepository.saveAndFlush(Mockito.any(Client.class))).thenReturn(client);
		Client newClient = clientServiceImpl.createClient(client);
		assertThat(newClient.getDocumentNumber()).isSameAs(client.getDocumentNumber());
	}

	@Test
	public void updateClient() {
		Optional<Client> client = Optional.of(Client.builder().id(Long.valueOf(1)).documentNumber("1").documentType("1")
				.name("Juan").lastName("Pena").sex("M").age("15").build());
		Mockito.when(clientRepository.findById(Long.valueOf(1))).thenReturn(client);
		Mockito.when(clientRepository.saveAndFlush(Mockito.any(Client.class))).thenReturn(client.get());
		Client newClient = clientServiceImpl.updateClient(client.get());
		assertThat(newClient.getId()).isSameAs(newClient.getId());
	}

	@Test
	public void deleteClient() {
		Optional<Client> client = Optional.of(Client.builder().id(Long.valueOf(1)).documentNumber("1").documentType("1")
				.name("Juan").lastName("Pena").sex("M").age("15").build());
		Mockito.when(clientRepository.findById(Long.valueOf(1))).thenReturn(client);
		clientServiceImpl.deleteClient("1");
	}

}
