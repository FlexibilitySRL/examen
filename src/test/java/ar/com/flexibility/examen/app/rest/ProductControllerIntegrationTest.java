package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductServiceImpl productService;


    private static long PRODUCTS_COUNT;
    private static long PRODUCT_ID_EXIST_IN_DB;
    private static long PRODUCT_ID_NOT_EXIST_IN_DB;

    @Before
    public void setUp(){
        productService.deleteAll();

        Product product = new Product();
        product.setDescription("first product");
        product.setPrice(new BigDecimal(2.50));

        productService.add(product);

        List<Product> productList = productService.findAll();

        PRODUCTS_COUNT = productList.size();
        PRODUCT_ID_EXIST_IN_DB = productList.get((int) PRODUCTS_COUNT-1).getId();
        PRODUCT_ID_NOT_EXIST_IN_DB = PRODUCT_ID_EXIST_IN_DB + 1;
    }


    @Test
    public void testFindAll() throws Exception {

        mvc.perform(get("/products/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void testFindOneOk() throws Exception {
        //given
        String pathUrl = String.format("/products/%s", PRODUCT_ID_EXIST_IN_DB);

        mvc.perform(get(pathUrl).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindOneNotFound() throws Exception {
        //given
        String pathUrl = String.format("/products/%s", PRODUCT_ID_NOT_EXIST_IN_DB);

        mvc.perform(get(pathUrl).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    //TODO: tests add update delete

}
