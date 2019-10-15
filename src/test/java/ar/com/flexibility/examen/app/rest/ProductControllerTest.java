package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.app.api.Account;
import ar.com.flexibility.examen.app.api.Address;
import ar.com.flexibility.examen.app.api.Customer;
import ar.com.flexibility.examen.app.api.Product;
import ar.com.flexibility.examen.domain.persistence.CustomerRepository;
import ar.com.flexibility.examen.domain.persistence.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;


    @Before
    public void setUp() throws Exception {
        Product product = Product.builder().price(BigDecimal.ONE).build();
        ar.com.flexibility.examen.domain.model.Product entity = mapper.map(product, ar.com.flexibility.examen.domain.model.Product.class);
        repository.save(entity);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addCustomer() throws Exception {

        Product product = Product.builder().price(BigDecimal.ONE).build();

        mockMvc.perform(post("/product/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk());

    }

    @Test
    public void findAllProducts() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/product/")).andExpect(status().isOk()).andReturn();

        JsonNode jsonNode = new ObjectMapper().readTree(mvcResult.getResponse().getContentAsString());

        assertTrue(jsonNode.size() == 1);
    }

    @Test
    public void updateProduct() throws Exception {

        Product product = Product.builder().price(BigDecimal.ONE).build();
        ar.com.flexibility.examen.domain.model.Product entity = mapper.map(product, ar.com.flexibility.examen.domain.model.Product.class);
        repository.save(entity);

        mockMvc.perform(put("/product/" + entity.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteProduct() throws Exception {

        mockMvc.perform(delete("/product/" + 1000)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
    }
}