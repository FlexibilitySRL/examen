package ar.com.plug.examen.integration;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.rest.ClientController;
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
    public void testGetClients() {

        ResponseEntity response = clientController.getClients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((ArrayList) response.getBody()).size());
    }

    @Test
    public void testCreateClient() {

        ClientApi clientApi = new ClientApi("John", "Smith");

        clientController.createClient(clientApi);

        ResponseEntity response = clientController.getClients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((ArrayList) response.getBody()).size());
    }
}
