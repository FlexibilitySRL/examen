package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exceptions.*;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientServiceIntegrationTest {

    @Autowired
    private ClientServiceImpl service;

    Client aClientMock;

    @BeforeEach
    public void setUp() throws InvalidDocumentNumberException, EmptyNameException, EmptyLastNameException {
        aClientMock = new Client("aName", "aLastName", "1234");
    }

    @Test
    public void addAProductTest(){
        Client aClientSaved = service.saveClient(aClientMock);

        assertEquals(aClientSaved.getName(), "aName" );
        assertEquals(aClientSaved.getLastName(), "aLastName" );
        assertEquals(aClientSaved.getDocument(), "1234" );
    }

}
