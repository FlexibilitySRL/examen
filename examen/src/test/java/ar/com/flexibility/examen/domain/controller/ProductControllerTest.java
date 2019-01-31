package ar.com.flexibility.examen.domain.controller;

import ar.com.flexibility.examen.app.rest.ProductController;
import com.example.core.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ProductControllerTest {
    @InjectMocks
    private ProductController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getProductTest() throws Exception {
        Product product = Product.getProductDefault();

        this.mockMvc.perform(post("/crear_producto")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(product))
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getProductsTest() throws Exception {
        this.mockMvc.perform(get("/lista_produtos")
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
