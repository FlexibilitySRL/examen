package ar.com.flexibility.examen.app.rest;


import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ClientController controller;

    @Mock
    private ClientService service;

    {
        service = Mockito.mock(ClientService.class);
    }

    @BeforeEach
    void initUseCase(){
        controller = new ClientController(service);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void getClient() throws Exception {
        Client client = new Client();
        client.setId(1234567L);
        client.setEmail("email");
        client.setName("name");

        when(service.findById(1234567L)).thenReturn(client);

        ResultActions result = mockMvc.perform(get("/rest/clients/{id}",1234567L))
                                               .andExpect(status().isOk())
                                               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(service).findById(1234567L);
        verifyNoMoreInteractions(service);

        result.andExpect(content().json("{'id' : 1234567, 'name':'name','email':'email'}"));
    }

    @Test
    void getClientThatDoesNotExist() throws Exception {

        when(service.findById(1234567L)).thenReturn(null);

        ResultActions result = mockMvc.perform(get("/rest/clients/{id}",1234567L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=ISO-8859-1")));

        verify(service).findById(1234567L);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteClient() throws Exception {
        ResultActions result = mockMvc.perform(
                delete("/rest/clients/{id}", 1))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteById(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    void createClient() throws Exception {
        Client client = new Client();
        client.setId(1234567L);
        client.setEmail("email");
        client.setName("name");

        ClientApi clientApi = new ClientApi();
        clientApi.setId(1234567L);
        clientApi.setName("name");
        clientApi.setEmail("email");

        when(service.create(any(Client.class))).thenReturn(client);

        ResultActions result = mockMvc.perform(post("/rest/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clientApi))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(service).create(any(Client.class));
        verifyNoMoreInteractions(service);

        result.andExpect(content().json("{'id' : 1234567, 'name':'name', 'email':'email'}"));
    }

    @Test
    void updateClient() throws Exception {

        Client client = new Client();
        client.setId(1234567L);
        client.setEmail("email");
        client.setName("name");

        when(service.update(any(Client.class))).thenReturn(client);

        ClientApi clientApi = new ClientApi();
        clientApi.setId(1234567L);
        clientApi.setName("name");
        clientApi.setEmail("email");

        ResultActions result = mockMvc.perform(put("/rest/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clientApi))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).update(any(Client.class));
        verifyNoMoreInteractions(service);

        result.andExpect(content().json("{'id' : 1234567, 'name':'name', 'email':'email'}"));
    }

    @Test
    void updateClientWithEmptyId() throws Exception {

        ClientApi clientApi = new ClientApi();
        clientApi.setId(null);
        clientApi.setName("name");
        clientApi.setEmail("email");

        ResultActions result = mockMvc.perform(put("/rest/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clientApi))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("El cliente no existe"));

        verifyNoMoreInteractions(service);
    }

    @Test
    void getAllClients() throws Exception {
        Client client1 = new Client();
        client1.setId(1234567L);
        client1.setEmail("email_1");
        client1.setName("name_1");

        Client client2 = new Client();
        client2.setId(7654321L);
        client2.setEmail("email_2");
        client2.setName("name_2");

        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);

        when(service.findAll()).thenReturn(clients);

        ResultActions result = mockMvc.perform(
                get("/rest/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(service).findAll();
        verifyNoMoreInteractions(service);

        result.andExpect(content().json("[{'id' : 1234567, 'name':'name_1','email':'email_1'},"
                                                  + "{'id' : 7654321, 'name':'name_2','email':'email_2'}]"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}