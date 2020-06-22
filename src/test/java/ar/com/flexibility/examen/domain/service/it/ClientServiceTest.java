package ar.com.flexibility.examen.domain.service.it;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientServiceTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClientService clientService;
    private Client client;
    private ClientApi clientApiNew;
    private ClientApi clientApiUpdated;

    @Before
    public void setUp() {
        client = new Client();
        client.setName("Martin");
        client = entityManager.merge(client);
        clientApiNew = new ClientApi();
        clientApiNew.setName("Martin");
        clientApiUpdated = new ClientApi(client.getId(), "Nicanor");
    }

    @Test
    public void shouldGetAllClients() {
        List<ClientApi> clientList = clientService.all();
        assertFalse(clientList.isEmpty());
    }

    @Test
    public void shouldGetAClient() {
        ClientApi clientApi = clientService.get(client.getId());
        assertNotNull(clientApi);
        assertEquals(client.getId(), clientApi.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenGettingAClient() {
        clientService.get(-1L);
    }

    @Test
    public void shouldCreateNewClient() {
        ClientApi created = clientService.create(clientApiNew);
        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test
    public void shouldUpdateClient() {
        ClientApi updated = clientService.update(client.getId(), clientApiUpdated);
        assertNotNull(updated);
        assertEquals(clientApiUpdated.getId(), updated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenUpdatingAClient() {
        clientService.update(-1L, clientApiNew);
    }

    @Test(expected = NotFoundException.class)
    public void shouldRemoveClient() {
        clientService.remove(clientApiUpdated.getId());
        clientService.get(clientApiUpdated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundRemovingANotFoundClient() {
        clientService.remove(-1L);
    }
}
