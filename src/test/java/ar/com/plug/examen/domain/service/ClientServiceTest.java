package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.ClientNotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void testGetAll() {
        clientService.getAll();

        verify(clientRepository).findAll();
    }

    @Test
    public void testSave() {

        clientService.save(new Client("firstName", "lastName"));

        verify(clientRepository).save(any(Client.class));
    }

    @Test
    public void testFindById() {

        when(clientRepository.findById(1L)).thenReturn(Optional.of(new Client("firstName", "lastName")));

        clientService.findById(1);

        verify(clientRepository).findById(1L);
    }

    @Test
    public void testFindByIdWhenClientDoesntExist() {

        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            clientService.findById(1);
            fail();
        } catch (ClientNotFoundException ex) {
            assertEquals("Client not found", ex.getMessage());
        }

        verify(clientRepository).findById(1L);
    }

    @Test
    public void testDelete() {

        clientService.deleteById(1);

        verify(clientRepository).deleteById(1L);
    }
}
