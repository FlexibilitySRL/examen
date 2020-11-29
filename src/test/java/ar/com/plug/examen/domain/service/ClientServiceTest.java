/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.rest.ClientController;
import ar.com.plug.examen.domain.model.Client;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msulbara
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientController clientController;

    @Test
    @Transactional
    public void create() {
        Client c = new Client();
        c.setName("Jon");
        c.setLastName("Doe");
        Client saved = clientController.createClient(c).getBody();

        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    @Transactional
    public void delete() {
        Client c = new Client();
        c.setId(2L);
        c.setName("Jon");
        c.setLastName("Doe");

        clientController.createClient(c).getStatusCode();
        HttpStatus code = clientController.deleteClient(2L).getStatusCode();
        assertEquals(HttpStatus.OK, code);
    }
    
    @Test
    @Transactional
    public void update() {
        Client c = new Client();
        c.setId(4L);
        c.setName("Jon");
        c.setLastName("Doe");
        
        Client savedClient = clientController.createClient(c).getBody();
        savedClient.setName("Maria");
        
        Client updatedClient = clientController.updateClient(savedClient).getBody();
        
        assertEquals("Maria", updatedClient.getName());
    }
}
