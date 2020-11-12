package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientTest {
    Client aClient;

    @Test
    public void clientWithEmptyNameShouldRaiseEmptyNameException(){
        assertThrows(EmptyNameException.class, ()-> {
            aClient = new Client("", "aLastName", "aDocument");
        });
    }

    @Test
    public void clientWithEmptyLastNameShouldRaiseEmptyLastNameException(){
        assertThrows(EmptyLastNameException.class, ()-> {
            aClient = new Client("aName", "", "aDocument");
        });
    }

    @Test
    public void clientWithEmptyDocumentNumberShouldRaiseInvalidDocumentNumberException(){
        assertThrows(InvalidDocumentNumberException.class, ()-> {
            aClient = new Client("aName", "aLastName", "");
        });
    }

    @Test
    public void clientWithDocumentNumberWithStringShouldRaiseInvalidDocumentNumberException(){
        assertThrows(InvalidDocumentNumberException.class, ()-> {
            aClient = new Client("aName", "aLastName", "aDocument");
        });
    }

    @Test
    public void creationalTestProduct() throws InvalidDocumentNumberException, EmptyNameException, EmptyLastNameException {
        aClient = new Client("aName", "aLastName", "1234");
        assertEquals(aClient.getName(), "aName");
        assertEquals(aClient.getLastName(), "aLastName");
        assertEquals(aClient.getDocument(), "1234");
    }

    @Test
    public void givenTwoClientsWithTheSameDocumentNumberWhenComparedThemByEqualsReturnsTrue() throws InvalidDocumentNumberException, EmptyNameException, EmptyLastNameException {
        aClient = new Client("aName", "aLastName", "1234");
        Client anotherClient = new Client("anotherName", "anotherLastName", "1234");

        assertEquals(aClient, anotherClient);
    }

    @Test
    public void givenTwoClientsWithDifferentDocumentNumberWhenComparedThemByEqualsReturnsFalse() throws InvalidDocumentNumberException, EmptyNameException, EmptyLastNameException {
        aClient = new Client("aName", "aLastName", "1234");
        Client anotherClient = new Client("anotherName", "anotherLastName", "12345");

        assertNotEquals(aClient, anotherClient);
    }
}

