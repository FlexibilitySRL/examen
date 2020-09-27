package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.exception.ClientNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplCRUDTest {

    @Autowired
    private ClientService clientService;


    @Test
    public void createClientTest() {
        Client client = getClientForTest();

        Client createdClient = clientService.createClient(client);

        assertNotNull(createdClient.getId());
    }

    @Test( expected = ClientNotFoundException.class)
    public void deleteClientTest() throws ClientNotFoundException {
        Client client = getClientForTest();
        client = clientService.createClient(client);
        clientService.deleteClient(client.getId());
        clientService.getClient(client.getId());
    }

    @Test(expected = ClientNotFoundException.class)
    public void getClientTest__whenIsUnexisting() throws ClientNotFoundException {
        clientService.getClient(123L);
    }

    @Test
    public void getClientTest__whenItExists() throws ClientNotFoundException {
        Client client = getClientForTest();
        clientService.createClient(client);
        clientService.getClient(client.getId());
    }

    @Test
    public void updateClientTest() throws ClientNotFoundException {
        Client client = getClientForTest();
        client = clientService.createClient(client);

        client.setFirstName("Jacky");
        clientService.updateClient(client);

        client = clientService.getClient(client.getId());

        assertEquals("Jacky", client.getFirstName());
    }


    private Client getClientForTest() {
        Client client = new Client();
        client.setFirstName("Jack");
        client.setLastName("Sparrow");
        client.setAge("34");

        return client;
    }

}
