package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.rest.clientController;
import ar.com.plug.examen.domain.model.clientModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class clientServiceTest {

    @Test
    void saveClient() {

        clientModel clientModel = new clientModel("1010243260","Fernando", "email");

        clientModel clientresult = new clientModel();

        clientService clientService = Mockito.mock(clientService.class);
        when(clientService.saveClient(clientModel)).thenReturn(clientresult);

        assertNotNull(clientresult);

        assertEquals(clientModel.getIdentification() ,clientresult.getIdentification());
        assertEquals(clientModel.getName() ,clientresult.getName());
        assertEquals(clientModel.getEmail(),clientresult.getEmail());

    }
}