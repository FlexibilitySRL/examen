package ar.com.flexibility.examen.domain.controller;

import ar.com.flexibility.examen.app.rest.SaleController;
import com.example.core.model.Sale;
import com.example.core.model.SaleDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class SaleControllerTest {
    @InjectMocks
    private SaleController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void confirmBuyTest() throws Exception {
        String idSale = "1";
        this.mockMvc.perform(post("/confirmar_compra")
                .contentType(MediaType.APPLICATION_JSON)
                .requestAttr("idSale", idSale));
    }

    @Test
    public void addToCartTest() throws Exception {
        Long idSale = 1L;
        List<SaleDetail> details = Sale.getDetailsDefault();
        this.mockMvc.perform(post("/agregar_producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(idSale))
                .content(asJsonString(details))
                .accept(MediaType.APPLICATION_JSON));
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
