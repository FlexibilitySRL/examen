package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.service.ProductService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private Gson gson;
    private final String URL = "/products";

    private ProductApi productApi = new ProductApi(20L,"Oven", "for cooking");
    private ProductApi productApi2 = new ProductApi(21L, "Air conditioner", "for cooling");

    @Test
    public void okWhenSearchingForAllProducts() throws Exception {
        List<ProductApi> productList = new ArrayList<>();
        productList.add(productApi2);
        productList.add(productApi);

        when(productService.all()).thenReturn(productList);

        this.mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(productApi2.getId()))
                .andExpect(jsonPath("$.[0].name").value(productApi2.getName()))
                .andExpect(jsonPath("$.[0].description").value(productApi2.getDescription()))
                .andExpect(jsonPath("$.[1].id").value(productApi.getId()))
                .andExpect(jsonPath("$.[1].name").value(productApi.getName()))
                .andExpect(jsonPath("$.[1].description").value(productApi.getDescription()));
    }

    @Test
    public void okWhenSearchingForProduct() throws Exception {
        when(productService.get(anyLong())).thenReturn(productApi);

        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productApi.getId()))
                .andExpect(jsonPath("$.name").value(productApi.getName()));
    }

    @Test
    public void notFoundWhenSearchingForProduct() throws Exception {
        when(productService.get(anyLong())).thenThrow(new NotFoundException("Product with id 10"));

        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void okWhenCreatingANewProduct() throws Exception {
        String json = gson.toJson(productApi);

        when(productService.create(any())).thenReturn(productApi);

        this.mockMvc.perform(post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(productApi.getName()));
    }

    @Test
    public void badRequestWhenCreatingAnAlreadyExistingProduct() throws Exception {
        String json = gson.toJson(productApi);

        when(productService.create(any())).thenThrow(new BadRequestException("The product already exist"));

        this.mockMvc.perform(post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void okWhenUpdatingAnExistingProduct() throws Exception {
        String json = gson.toJson(productApi);

        when(productService.update(anyLong(), any())).thenReturn(productApi);

        this.mockMvc.perform(put(URL + "/10").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productApi.getId()))
                .andExpect(jsonPath("$.name").value(productApi.getName()));
    }

    @Test
    public void notFoundWhenUpdatingANoneExistingProduct() throws Exception {
        String json = gson.toJson(productApi);

        when(productService.update(anyLong(), any())).thenThrow(new NotFoundException("Product with id 10"));

        this.mockMvc.perform(put(URL + "/10").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void okWhenRemovingAnExistingProduct() throws Exception {
        doNothing().when(productService).remove(anyLong());
        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isOk());
    }

    @Test
    public void notFoundWhenRemovingANoneExistingProduct() throws Exception {
        doThrow(new NotFoundException("Product with id 10")).when(productService).remove(anyLong());
        this.mockMvc.perform(delete(URL + "/10"))
                .andExpect(status().isNotFound());
    }
}
