package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testGetClients() throws Exception {

        mockMvc.perform(get("/clients")).andExpect(status().isOk());

        verify(clientService).getAll();
    }

    @Test
    public void testGetClientById() throws Exception {

        mockMvc.perform(get("/clients/1")).andExpect(status().isOk());

        verify(clientService).findById(1);
    }

    @Test
    public void testCreateClient() throws Exception {

        mockMvc.perform(post("/clients")
                        .content(new ObjectMapper().writeValueAsString(new ClientApi("fistName1", "lastname1")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(clientService).save(any(Client.class));
    }

    @Test
    public void testCreateClientWithEmptyRequiredField() throws Exception {

        mockMvc.perform(post("/clients")
                        .content(new ObjectMapper().writeValueAsString(new ClientApi(null, "lastname1")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(clientService, times(0)).save(any(Client.class));
    }

    @Test
    public void testUpdateClient() throws Exception {

        when(clientService.findById(1)).thenReturn(new Client("oldFirstName", "oldLastName"));

        mockMvc.perform(put("/clients/1")
                        .content(new ObjectMapper().writeValueAsString(new ClientApi("fistName1", "lastname1")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService).findById(1);
        verify(clientService).save(any(Client.class));
    }

    @Test
    public void testDeleteClient() throws Exception {

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isNoContent());

        verify(clientService).deleteById(1);
    }
}
