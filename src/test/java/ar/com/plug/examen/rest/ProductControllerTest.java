package ar.com.plug.examen.rest;

import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    // Test data
    private final Product testProduct = new Product(1L, "Test Product", "Description", new BigDecimal(10));

    @Test
    void createProduct() throws Exception {
        // Mock service behavior
        when(productService.createProduct(any())).thenReturn(testProduct);

        // Perform request
        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"name\": \"Test Product\",\n" +
                                "  \"description\": \"Description\",\n" +
                                "  \"price\": 10\n" +
                                "}\n"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Product"));

        verify(productService).createProduct(any()); // Verify service interaction
    }

    // ... Add similar tests for getProductById, getAllProducts, updateProduct, and deleteProduct
}
