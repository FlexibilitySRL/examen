package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTest {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ProductServiceImpl productService;

    private Client client;

    @Before
    public void initializer(){
        client = new Client("Ruperto","Lazarreta");
    }

    @Test
    public void addClient_withoutProducts_returnClient(){
        Client savedClient = clientService.addClient(this.client);

        assertEquals(client.getName(), savedClient.getName());
        assertEquals(client.getSurname(), savedClient.getSurname());
    }

    @Test
    public void addClient_clientWithProducts_returnClient(){
        Product product = new Product("coca", BigDecimal.valueOf(12.3));
        productService.addProduct(product);

        client.addProduct(product);
        Client savedClient = clientService.addClient(this.client);

        assertEquals(client.getProducts().size(), savedClient.getProducts().size());
        assertEquals("coca", client.getProducts().get(0).getName());
    }

    @Test
    public void updateClient_validClient_returnClient(){
        Client savedClient = clientService.addClient(this.client);

        savedClient.setName("Enrique");
        savedClient.setSurname("Zapata");

        Client updatedClient = clientService.updateClient(savedClient);

        assertEquals("Enrique", updatedClient.getName());
        assertEquals("Zapata", updatedClient.getSurname());
    }

    @Test
    public void findClient_withValidId_returnClient(){
        Client savedClient = clientService.addClient(client);

        Client searchedClient = clientService.findById(savedClient.getId());

        assertNotNull(searchedClient);
        assertEquals("Ruperto", searchedClient.getName());
    }

    @Test
    public void deleteClient_withExistingClient() {
        Client savedClient = clientService.addClient(this.client);

        clientService.deleteClient(savedClient.getId());

        assertNull(clientService.findById(savedClient.getId()));
    }

}
