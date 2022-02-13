package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
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

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProducts() throws Exception {

        mockMvc.perform(get("/products")).andExpect(status().isOk());

        verify(productService).getAll();
    }

    @Test
    public void testetProductById() throws Exception {

        mockMvc.perform(get("/products/1")).andExpect(status().isOk());

        verify(productService).findById(1);
    }

    @Test
    public void testCreateProduct() throws Exception {

        mockMvc.perform(post("/products")
                        .content(new ObjectMapper().writeValueAsString(new ProductApi("product1", 10.10)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(productService).save(any(Product.class));
    }

    @Test
    public void testCreateProductWithEmptyRequiredField() throws Exception {

        mockMvc.perform(post("/products")
                        .content(new ObjectMapper().writeValueAsString(new ProductApi("", 10.10)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(productService, times(0)).save(any(Product.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {

        when(productService.findById(1)).thenReturn(new Product("oldFirstName", 50.00));

        mockMvc.perform(put("/products/1")
                        .content(new ObjectMapper().writeValueAsString(new ProductApi("product1", 10.10)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).findById(1);
        verify(productService).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());

        verify(productService).deleteById(1);
    }

}
