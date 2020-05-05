package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void whenFindAll_thenReturnList() {
        List<Client> clients = getDummyClients();

        given(clientRepository.findAll()).willReturn(clients);

        List<Client> pulledClients = clientService.retrieveClients();

        assertThat(pulledClients).hasSize(clients.size());
    }

    @Test
    public void whenFindExistingClient_thenReturnClient() {
        List<Client> clients = getDummyClients();

        given(clientRepository.findOne(1L)).willReturn(clients.get(0));

        Client foundClient = clientService.retrieveClientById(1L);

        assertThat(foundClient).isEqualTo(clients.get(0));
    }

    @Test
    public void whenFindNonExistingClient_thenReturnNull() {
        given(clientRepository.findOne(3L)).willReturn(null);

        Client foundClient = clientService.retrieveClientById(6L);

        assertThat(foundClient).isEqualTo(null);
    }

    @Test
    public void whenAddingClient_thenItHasAnId() {
        Client client = new Client(1L, "Amador", "Cuenca", "Fake Street 123");
        Client clientWithId = new Client(1L, "Amador", "Cuenca", "Fake Street 123");
        List<Client> clients = new ArrayList<>();

        given(clientRepository.findAll()).willReturn(clients);

        assertThat(clientService.retrieveClients()).hasSize(0);

        clients.add(clientWithId);

        given(clientRepository.save(client)).willReturn(clientWithId);

        Client savedClient = clientService.addClient(client);

        given(clientRepository.findAll()).willReturn(clients);

        List<Client> retrievedProducts = clientService.retrieveClients();

        assertThat(retrievedProducts).hasSize(1);
        assertThat(savedClient.getId()).isEqualTo(1L);
    }

    @Test
    public void whenUpdatingExistingClient_thenItHasFieldsUpdated() {
        List<Client> clients = getDummyClients();

        Client originalClient = clients.get(0);

        Client updatedClient = new Client(1L, "Amador", "Cuenca", "Fake Street 456");

        given(clientRepository.exists(1L)).willReturn(true);
        given(clientRepository.save(originalClient)).willReturn(updatedClient);

        Client retrievedClient = clientService.updateClient(originalClient.getId(), originalClient);

        assertThat(retrievedClient.getAddress()).isEqualTo("Fake Street 456");
    }

    @Test
    public void whenUpdatingNonExistingClient_thenReturnNull() {
        List<Client> clients = getDummyClients();

        Client client = new Client(3L, "Luke", "Skywalker", "In a galaxy far far away...");

        given(clientRepository.exists(3L)).willReturn(false);

        Client retrievedClient = clientService.updateClient(3L, client);

        assertThat(retrievedClient).isEqualTo(null);
    }

    @Test
    public void whenDeletingExistingClient_thenReturnTrue() {
        given(clientRepository.exists(1L)).willReturn(true);

        assertThat(clientService.deleteClient(1L)).isTrue();
    }

    @Test
    public void whenDeletingNonExistingClient_thenReturnFalse() {
        given(clientRepository.exists(3L)).willReturn(false);

        assertThat(clientService.deleteClient(3L)).isFalse();
    }

    private List<Client> getDummyClients() {
        List<Client> clients = new ArrayList<>();

        clients.add(new Client(1L, "Amador", "Cuenca", "Fake Street 123"));
        clients.add(new Client(2L, "Mike", "Mayer", "Somewhere in California"));

        return clients;
    }
}