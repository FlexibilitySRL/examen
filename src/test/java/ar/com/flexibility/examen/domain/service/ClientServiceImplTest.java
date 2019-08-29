package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
class ClientServiceImplTest {

    private ClientRepository repo = Mockito.mock(ClientRepository.class);

    @InjectMocks
    private ClientService service;

    @BeforeEach
    void initUseCase() { service = new ClientServiceImpl(repo);}

    @Test
    void createClient() {
        Client client = new Client();
        client.setDni(3125109L);
        client.setName("alberto");
        client.setEmail("albertok@gmail.com");

        when(repo.save(client)).thenReturn(client);

        Client result = service.create(client);

        verify(repo).save(client);
        verifyNoMoreInteractions(repo);

        assertNotNull(result);
        assertEquals(result.getDni(), client.getDni());
        assertEquals(result.getName(), client.getName());
        assertEquals(result.getEmail(), client.getEmail());
    }

    @Test
    void updateClient() {
        Client original = new Client();
        original.setDni(31251409L);
        original.setName("alberto");
        original.setEmail("albertok@gmail.com");

        Client toUpdate = new Client();
        toUpdate.setDni(31251409L);
        toUpdate.setName("alberto");
        toUpdate.setEmail("alberto_k_19@gmail.com");

        when(repo.findOne(31251409L)).thenReturn(original);
        when(repo.save(original)).thenReturn(original);

        Client updatedClient = service.update(toUpdate);

        verify(repo).findOne(31251409L);
        verify(repo).save(original);
        verifyNoMoreInteractions(repo);

        assertEquals(updatedClient.getDni(), toUpdate.getDni());
        assertEquals(updatedClient.getName(), toUpdate.getName());
        assertEquals(updatedClient.getEmail(), toUpdate.getEmail());

    }

    @Test
    void findById() {
        Client client = new Client();
        client.setDni(31251409L);
        client.setName("alberto");
        client.setEmail("albertok@gmail.com");

        when(repo.findOne(31251409L)).thenReturn(client);

        Client found = service.findById(31251409L);

        verify(repo).findOne(31251409L);
        verifyNoMoreInteractions(repo);

        assertEquals(found,client);
        assertEquals(found.getDni(),client.getDni());
        assertEquals(found.getName(),client.getName());
        assertEquals(found.getEmail(),client.getEmail());
    }

    @Test
    void findAll() {
        Client client1 = new Client();
        client1.setDni(31251409L);
        client1.setName("alberto");
        client1.setEmail("albertok@gmail.com");

        Client client2 = new Client();
        client2.setDni(31251409L);
        client2.setName("alberto");
        client2.setEmail("albertok@gmail.com");

        List<Client> clients = new ArrayList<Client>();
        clients.add(client1);
        clients.add(client2);

        when(repo.findAll()).thenReturn(clients);
        List<Client> clientsFound = service.findAll();
        verify(repo).findAll();
        verifyNoMoreInteractions(repo);

        assertEquals(clientsFound,clients);
    }

    @Test
    void deleteById() {
        service.deleteById(43L);
        verify(repo).delete(43L);
        verifyNoMoreInteractions(repo);
    }
}