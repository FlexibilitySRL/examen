package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.utils.ProductBuilder;
import ar.com.flexibility.examen.utils.category.UnitTest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProductController.class)
@Import(ConfigTest.class)
public class ProductControllerTest {

    @Autowired
    private ProductService serviceMock;

    @Autowired
    private ProductController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    private ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Test
    @Category(UnitTest.class)
    public void addNewProductOKCase() throws Exception {
        final String requestJson = jsonMapper.writeValueAsString(ProductBuilder.createDto(1L, "ProductoPrueba"));
        final Product productRq = ProductBuilder.create(1L, "ProductoPrueba", null);
        final Product productRs = ProductBuilder.create(1L, "ProductoPrueba", "DescripcionGenerica");
        when(serviceMock.create(productRq)).thenReturn(productRs);
        mockMvc.perform(
                post("/product/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"ProductoPrueba\",\"description\":\"DescripcionGenerica\"}")));
        verify(serviceMock, times(1)).create(productRq);
    }

    @Test
    @Category(UnitTest.class)
    public void getProductOKCase() throws Exception {

        final Product product = ProductBuilder.create(1L, "ProductoPrueba", null);
        when(serviceMock.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/product/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"ProductoPrueba\",\"description\":null}")));

        verify(serviceMock, times(1)).findById(1L);
    }

    @Test
    @Category(UnitTest.class)
    public void deleteProductOKCase() throws Exception {
        when(serviceMock.delete(1L)).thenReturn(Boolean.TRUE);

        mockMvc.perform(delete("/product/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));

        verify(serviceMock, times(1)).delete(1L);
    }
}


