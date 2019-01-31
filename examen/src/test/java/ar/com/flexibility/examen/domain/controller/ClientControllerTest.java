package ar.com.flexibility.examen.domain.controller;

import ar.com.flexibility.examen.app.rest.ClientController;
import com.example.core.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ClientControllerTest {

    @InjectMocks
    private ClientController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void signupTest() throws Exception {
        Client client = Client.getClientDefault();
        this.mockMvc.perform(post("/detalle_cuenta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(client))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void informationTest() throws Exception {
        String param = "username";
        this.mockMvc.perform(post("/detalle_cuenta")
                .contentType(MediaType.APPLICATION_JSON)
                .param("username", param));
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
