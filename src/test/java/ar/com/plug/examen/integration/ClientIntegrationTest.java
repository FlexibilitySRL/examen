package ar.com.plug.examen.integration;

import ar.com.plug.examen.app.rest.ClientController;
import ar.com.plug.examen.domain.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientIntegrationTest {

    @Autowired
    private ClientController clientController;

    @Test
    public void testCreateClient() {

        Client client = new Client("John", "Smith");

        clientController.createClient(client);

        ResponseEntity response = clientController.getClients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, ((ArrayList) response.getBody()).size());
    }
}
