package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ClientServiceMockTest {

    @Mock
    private ClientRepository clientRepository;
    private ClientService clientService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        clientService = new ClientServiceImpl(clientRepository);
        Client client = Client.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Perez")
                .email("juan@perez.com")
                .numberID("12345678")
                .build();

        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        Mockito.when(clientRepository.save(client)).thenReturn(client);
    }

    @Test
    public void whenValidGetID_ThenReturnClient(){
        Client found = clientService.getClient(1L);
        Assertions.assertThat(found.getFirstName()).isEqualTo("Juan");
    }


    @Test
    public void whenCreateClient_TheReturnClient(){

        Client client = Client.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Perez")
                .email("juan@perez.com")
                .numberID("12345678")
                .build();

        Mockito.when(clientService.createClient(client)).thenReturn(client);
    }

    @Test
    public void whenUpdateClient_TheReturnClientUpdated(){

        Client client = Client.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Perez")
                .email("juanperez@perez.com")
                .numberID("12345678")
                .build();
        Mockito.when(clientService.updateClient(client)).thenReturn(client);
    }


//    @Test
//    public void whenDeleteClient_TheReturnClientDeleted(){
//        Mockito.when(clientService.deleteClient(1L)).getMock();
//    }

}
