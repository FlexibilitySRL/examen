package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.app.api.Account;
import ar.com.flexibility.examen.app.api.Address;
import ar.com.flexibility.examen.app.api.Customer;
import ar.com.flexibility.examen.domain.model.LineItem;
import ar.com.flexibility.examen.domain.model.ShippingOrder;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.persistence.CustomerRepository;
import ar.com.flexibility.examen.domain.persistence.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@Transactional
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;


    @Before
    public void setUp() throws Exception {

        ar.com.flexibility.examen.domain.model.Customer customer = ar.com.flexibility.examen.domain.model.Customer.
                builder().email("prueba@mail.com").name("name").phone("1234").build();

        ar.com.flexibility.examen.domain.model.Address address = ar.com.flexibility.examen.domain.model.Address.
                builder().city("city").name("name").state("state").streetAddress("street address").zipCode("1234").build();

        ar.com.flexibility.examen.domain.model.Account account = ar.com.flexibility.examen.domain.model.Account.
                builder().billingAddress(address).build();

        customer.setAccount(account);

        customerRepository.save(customer);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addCustomer() throws Exception {

        Address address = Address.builder().city("city").name("name").state("state").streetAddress("street address").zipCode("1234").build();
        Account account = Account.builder().billingAddress(address).build();
        Customer customer = Customer.builder().account(account).email("prueba@mail.com").name("name").phone("1234").build();

        mockMvc.perform(post("/customer/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(customer)))
                .andExpect(status().isOk());

    }

    @Test
    public void findAllCustomers() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/customer/")).andExpect(status().isOk()).andReturn();

        JsonNode jsonNode = new ObjectMapper().readTree(mvcResult.getResponse().getContentAsString());

        assertTrue(jsonNode.size() == 1);
    }

    @Test
    public void updateCustomer() throws Exception {

        ar.com.flexibility.examen.domain.model.Customer customer = ar.com.flexibility.examen.domain.model.Customer.
                builder().email("prueba1@mail.com").name("name").phone("1111").build();

        ar.com.flexibility.examen.domain.model.Address address = ar.com.flexibility.examen.domain.model.Address.
                builder().city("city1").name("name1").state("state1").streetAddress("street address1").zipCode("12341").build();

        ar.com.flexibility.examen.domain.model.Account account = ar.com.flexibility.examen.domain.model.Account.
                builder().billingAddress(address).build();

        customer.setAccount(account);

        customerRepository.save(customer);

        mockMvc.perform(put("/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(customer)))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete("/customer/" + new Random().nextInt())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
    }

    @Test
    public void addProducts() throws Exception {

        ar.com.flexibility.examen.domain.model.Customer customer = ar.com.flexibility.examen.domain.model.Customer.
                builder().email("prueba@mail.com").name("name").phone("1234").build();

        ar.com.flexibility.examen.domain.model.Address address = ar.com.flexibility.examen.domain.model.Address.
                builder().city("city").name("name").state("state").streetAddress("street address").zipCode("1234").build();

        ar.com.flexibility.examen.domain.model.Account account = ar.com.flexibility.examen.domain.model.Account.
                builder().billingAddress(address).build();

        customer.setAccount(account);

        customerRepository.save(customer);

        Product product = Product.builder().description("productDescription").build();
        productRepository.save(product);

        ar.com.flexibility.examen.app.api.Product product1 = ar.com.flexibility.examen.app.api.Product.builder().
                price(BigDecimal.ONE).
                quantity(10).build();

        mockMvc.perform(post("/customer/" + customer.getId() + "/products/" + product.getId() + "/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(product1)))
                .andExpect(status().isOk());
    }


    @Test
    public void findAllProducts() throws Exception {

        ar.com.flexibility.examen.domain.model.Customer customer = ar.com.flexibility.examen.domain.model.Customer.
                builder().email("prueba@mail.com").name("name").phone("1234").build();

        ar.com.flexibility.examen.domain.model.Address address = ar.com.flexibility.examen.domain.model.Address.
                builder().city("city").name("name").state("state").streetAddress("street address").zipCode("1234").build();

        ar.com.flexibility.examen.domain.model.Account account = ar.com.flexibility.examen.domain.model.Account.
                builder().billingAddress(address).build();

        Product product = Product.builder().description("productDescription").build();

        LineItem item = LineItem.builder().price(BigDecimal.ONE).quantity(12).product(product).build();

        List<LineItem> items = new ArrayList<>();
        items.add(item);

        ShippingOrder shippingOrder = ShippingOrder.builder().account(account).lineItems(items).build();
        List<ShippingOrder> shippingOrders = new ArrayList<>();
        shippingOrders.add(shippingOrder);

        account.setShippingOrders(shippingOrders);

        customer.setAccount(account);

        customerRepository.save(customer);

        MvcResult mvcResult = mockMvc.perform(get("/customer/" + customer.getId() + "/orders/")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn();

        JsonNode jsonNode = new ObjectMapper().readTree(mvcResult.getResponse().getContentAsString());

        ArrayNode itemsNode = (ArrayNode) jsonNode.get(0).get("lineItems");

        assertTrue(itemsNode.size() == 1);

    }

    @Test
    public void checkoutOrder() throws Exception {

        ar.com.flexibility.examen.domain.model.Customer customer = ar.com.flexibility.examen.domain.model.Customer.
                builder().email("prueba@mail.com").name("name").phone("1234").build();

        ar.com.flexibility.examen.domain.model.Address address = ar.com.flexibility.examen.domain.model.Address.
                builder().city("city").name("name").state("state").streetAddress("street address").zipCode("1234").build();

        ar.com.flexibility.examen.domain.model.Account account = ar.com.flexibility.examen.domain.model.Account.
                builder().billingAddress(address).build();

        Product product = Product.builder().description("productDescription").build();

        LineItem item = LineItem.builder().price(BigDecimal.ONE).quantity(12).product(product).build();

        List<LineItem> items = new ArrayList<>();
        items.add(item);

        ShippingOrder shippingOrder = ShippingOrder.builder().account(account).lineItems(items).build();
        List<ShippingOrder> shippingOrders = new ArrayList<>();
        shippingOrders.add(shippingOrder);

        account.setShippingOrders(shippingOrders);

        customer.setAccount(account);

        customerRepository.save(customer);

        Address address1 = Address.builder().
                zipCode("1").streetAddress("streetAddress").state("state").name("name").city("city").build();

        mockMvc.perform(put("/customer/" + customer.getId() + "/orders/" + shippingOrder.getId() + "/checkout")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(address1)))
                .andExpect(status().isOk());

    }
}