package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.apache.commons.lang3.StringUtils;
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
public class ClientServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClientService clientService;
    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client();
        client.setName("Jose");

        client = entityManager.merge(client);
    }

    @Test
    public void create() {
        ClientApi clientApi = new ClientApi();
        clientApi.setName("Jessie");

        ClientApi created = clientService.create(clientApi);
        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test(expected = BadRequestException.class)
    public void create_throw() {
        ClientApi clientApi = new ClientApi();
        clientApi.setName(StringUtils.EMPTY);

        clientService.create(clientApi);
    }

    @Test
    public void retrieve() {
        ClientApi clientApi = clientService.retrieve(client.getId());
        assertNotNull(clientApi);
        assertEquals(client.getId(), clientApi.getId());
    }

    @Test(expected = NotFoundException.class)
    public void retrieve_wrongCode() {
        clientService.retrieve(client.getId() * -1);
    }

    @Test
    public void list() {
        List<ClientApi> clientList = clientService.list();
        assertFalse(clientList.isEmpty());
    }

    @Test(expected = NotFoundException.class)
    public void remove() {
        Client client = new Client();
        client.setName("Jose");
        client = entityManager.merge(client);

        clientService.remove(client.getId());

        clientService.retrieve(client.getId());
    }

    @Test
    public void update() {
        ClientApi clientApi = new ClientApi();
        clientApi.setId(client.getId());
        clientApi.setName("new name");

        ClientApi updated = clientService.update(client.getId(), clientApi);
        assertNotNull(updated);
        assertEquals(clientApi.getId(), updated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void update_wrongCode() {
        ClientApi clientApi = new ClientApi();
        clientApi.setId(client.getId());
        clientApi.setName("new name");

        clientService.update(client.getId() * -1, clientApi);
    }

    /*@Test(expected = BadRequestException.class)
    public void getAllTransactions_throw() {
        transactionRepository.findBySellerId(seller.getId() -1);
    }*/
}