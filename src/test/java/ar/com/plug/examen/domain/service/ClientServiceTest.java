package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.domain.mapper.ClientMapper;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Captor
    private ArgumentCaptor<Client> clientCaptor;


    @Test
    public void testAddValidClient() {
        ClientApi ClientRequest = ClientApi.builder()
                .name("Client Name")
                .email("c1@gmail.com")
                .build();
        Client Client = ClientMapper.toClient(ClientRequest);
        Client.setId(1L);
        when(clientRepository.save(any())).thenReturn(Client);

        ClientDto response = clientService.addClient(ClientRequest);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Client Name", response.getName());
        assertEquals("c1@gmail.com", response.getEmail());
    }

    @Test
    public void testAddNullClient() {
        ClientApi clientRequest = null;

        assertThrows(IllegalArgumentException.class, () -> {
            clientService.addClient(clientRequest);
        });
    }

    @Test
    public void testFindAllEmptyList() {
        // Arrange
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Client> result = clientRepository.findAll();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAllCorrectNumberOfClientResponses() {
        // Arrange
        List<Client> Clients = Arrays.asList(new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(Clients);

        // Act
        List<ClientDto> result = clientService.findAll();

        // Assert
        assertEquals(Clients.size(), result.size());
    }

    @Test
    void testFindClientById_ValidId_ReturnsClientDto() {
        // Arrange
        Long clientId = 1L;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // Act
        ClientDto result = clientService.findClientById(clientId);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindClientById_NullId_ReturnsNull() {
        // Arrange
        Long clientId = null;

        // Act
        ClientDto result = clientService.findClientById(clientId);

        // Assert
        assertNull(result);
    }

    @Test
    void testFindClientById_NonExistingId_ReturnsNull() {
        // Arrange
        Long clientId = 2L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        // Act
        ClientDto result = clientService.findClientById(clientId);

        // Assert
        assertNull(result);
    }
    @Test
    void testFindClientById_validId() {
        Long validId = 1L;
        Client mockClient = new Client();
        when(clientRepository.findById(validId)).thenReturn(Optional.of(mockClient));

        ClientDto result = clientService.findClientById(validId);

        assertNotNull(result);
    }

    @Test
    public void testUpdateExistingClient() {
        // Arrange
        Long id = 1L;
        ClientApi ClientRequest = ClientApi.builder()
                .name("Ordinary name")
                .email("c1@gmail.com")
                .build();
        Client existingClient = Client.builder()
                .id(1L)
                .name("Super Client")
                .email("super_client@gmail.com")
                .build();
        when(clientRepository.findById(id)).thenReturn(Optional.of(existingClient));

        // Act
        clientService.updateClient(id, ClientRequest);

        // Assert
        verify(clientRepository).save(clientCaptor.capture());
        assertEquals(ClientRequest.getEmail(), clientCaptor.getValue().getEmail());
        assertEquals(ClientRequest.getName(), clientCaptor.getValue().getName());
    }

    @Test
    public void testUpdateNonExistingClient() {
        // Arrange
        Long id = 2L;
        ClientApi clientRequest = new ClientApi();
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ClientDto updatedClientDto = clientService.updateClient(id, clientRequest);

        // Assert
        assertNull(updatedClientDto);
    }

    @Test
    void testDeleteClient_Exists() {
        Long id = 1L;
        Client client = new Client();
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        ClientDto result = clientService.deleteClient(id);

        verify(clientRepository).deleteById(id);
        assertNotNull(result);
    }

    @Test
    void testDeleteClient_NotExists() {
        Long id = 1L;
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        ClientDto result = clientService.deleteClient(id);

        verify(clientRepository, never()).deleteById(id);
        assertNull(result);
    }

    @Test
    void testDeleteClient_NullId() {
        Long id = null;

        ClientDto result = clientService.deleteClient(id);

        verify(clientRepository, never()).deleteById(any());
        assertNull(result);
    }
}