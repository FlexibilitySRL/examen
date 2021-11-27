package ar.com.plug.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.fixtures.ClientFixture;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.ClientMapper;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.domain.validators.Validator;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private Validator validator;

    @Test
    public void findAllTest() {
        List<ClientApi> listClients = ClientFixture
                .getClientApitList(ClientFixture.getClientApi(),
                        ClientFixture.getClientApi());
        when(this.clientMapper.clientsToListClientApi(this.clientRepository.findAll()))
                .thenReturn(listClients);

        List<ClientApi> response = this.clientService.findAll();
        assertEquals(listClients, response);
        verify(this.clientRepository, times(2)).findAll();
    }

    @Test
    public void findByIdCheckedTest() {
        Client client = ClientFixture.getClientWithId(1L);
        when(this.clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ClientApi response = this.clientService.findById(1L);
        assertEquals(this.clientMapper.clientToClientApi(client), response);
        verify(this.clientRepository, times(1)).findById(1L);
    }

    @Test
    public void findByIdChecked_NotFoundTest() {
        when(this.clientRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(GenericNotFoundException.class, () -> {
            this.clientService.findById(1L);
        });

        assertTrue(exception.getMessage().contains("Client not found"));
        verify(this.clientRepository, times(1)).findById(1L);
    }

    @Test
    public void save_successTest() {
        ClientApi clientMock = ClientFixture.getClientApiWithId(1L);
        Client client = ClientFixture.getClient();
        ClientApi clientToSave = ClientFixture.getClientApi();
        when(this.clientMapper.clientToClientApi(this.clientRepository.save(client))).thenReturn(clientMock);
        doNothing().when(this.validator).validateClient(clientToSave, Boolean.FALSE);

        ClientApi response = this.clientService.save(clientToSave);
        assertEquals(clientMock, response);
        verify(this.clientRepository, times(1)).save(client);
    }

    @Test
    public void save_badRequestTest() {
        ClientApi clientMock = ClientFixture.getClientApiWithId(1L);
        clientMock.setUserName(null);
        Client client = ClientFixture.getClient();
        GenericBadRequestException badRequestException = new GenericBadRequestException("The username is required");
        when(this.clientMapper.clientToClientApi(this.clientRepository.save(client))).thenReturn(clientMock);
        doThrow(badRequestException).when(this.validator).validateClient(any(), any());

        Exception exception = assertThrows(GenericBadRequestException.class, () -> {
            this.clientService.save(clientMock);
        });

        assertTrue(exception.getMessage().contains("The username is required"));
        verify(this.clientRepository, times(1)).save(client);
    }

    @Test
    public void update_successTest() {
        ClientApi clientMock = ClientFixture.getClientApiWithId(1L);
        Client client = ClientFixture.getClientWithId(1L);
        ClientApi clientToSave = ClientFixture.getClientApiWithId(1L);
        when(this.clientMapper.clientToClientApi(this.clientRepository.save(client))).thenReturn(clientMock);
        doNothing().when(this.validator).validateClient(clientToSave, Boolean.TRUE);
        when(this.clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ClientApi response = this.clientService.update(clientToSave);
        assertEquals(clientMock, response);
        verify(this.clientRepository, times(1)).save(client);
    }

    @Test
    public void update_badRequestTest() {
        ClientApi clientMock = ClientFixture.getClientApiWithId(1L);
        Client client = ClientFixture.getClientWithId(1L);
        GenericBadRequestException badRequestException = new GenericBadRequestException("The id is required");
        when(this.clientMapper.clientToClientApi(this.clientRepository.save(client))).thenReturn(clientMock);
        when(this.clientRepository.findById(1L)).thenReturn(Optional.of(client));
        doThrow(badRequestException).when(this.validator).validateClient(any(), any());

        Exception exception = assertThrows(GenericBadRequestException.class, () -> {
            this.clientService.update(clientMock);
        });

        assertTrue(exception.getMessage().contains("The id is required"));
        verify(this.clientRepository, times(1)).save(client);
    }

    @Test
    public void update_notFoundTest() {
        ClientApi clientMock = ClientFixture.getClientApiWithId(1L);
        Client client = ClientFixture.getClientWithId(1L);
        GenericNotFoundException genericNotFoundException = new GenericNotFoundException("The id is required");
        when(this.clientMapper.clientToClientApi(this.clientRepository.save(client))).thenReturn(clientMock);
        when(this.clientRepository.findById(1L)).thenReturn(Optional.of(client));
        doThrow(genericNotFoundException).when(this.validator).validateClient(any(), any());

        Exception exception = assertThrows(GenericNotFoundException.class, () -> {
            this.clientService.update(clientMock);
        });

        assertTrue(exception.getMessage().contains("The id is required"));
        verify(this.clientRepository, times(1)).save(client);
    }

    @Test
    public void deleteTest() {
        ClientApi clientMock = ClientFixture.getClientApiWithId(1L);
        Client client = ClientFixture.getClientWithId(1L);
        when(this.clientMapper.clientToClientApi(this.clientRepository.save(client))).thenReturn(clientMock);
        when(this.clientRepository.findById(1L)).thenReturn(Optional.of(client));

        this.clientService.delete(1L);

        assertFalse(this.clientService.findAll().contains(clientMock));
        verify(this.clientRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteTest_notFound() {
        GenericNotFoundException genericNotFoundException = new GenericNotFoundException("Client not found");
        doThrow(genericNotFoundException).when(this.clientRepository).findById(1L);

        Exception exception = assertThrows(GenericNotFoundException.class, () -> {
            this.clientService.delete(1L);
        });

        assertTrue(exception.getMessage().contains("Client not found"));
        verify(this.clientRepository, times(0)).deleteById(1L);
    }
}
