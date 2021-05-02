package ar.com.plug.examen;

import ar.com.plug.examen.app.rest.CustomerController;
import ar.com.plug.examen.datasource.model.Customer;
import ar.com.plug.examen.datasource.model.Product;
import ar.com.plug.examen.datasource.model.Purchase;
import ar.com.plug.examen.datasource.repo.CustomerRepo;
import ar.com.plug.examen.datasource.repo.ProductRepo;
import ar.com.plug.examen.datasource.repo.PurchaseRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles(profiles = "local")
public class IntegrationTest {

    private static final String DEFAULT_CUSTOMER_NAME = "Customer 101";
    private static final long DEFAULT_CUSTOMER_ID = 101L;
    private static final long DEFAULT_PRODUCT_ID = 201L;
    private static final String DEFAULT_PRODUCT_NAME = "Product 201";
    private static final long DEFAULT_PURCHASE_ID = 301L;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    public static final ObjectWriter OBJECT_WRITER = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer().withDefaultPrettyPrinter();

    private Customer defaultCustomer;
    private Product defaultProduct;
    private Purchase defaultPurchase;
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        defaultCustomer = Customer.builder().id(DEFAULT_CUSTOMER_ID).name(DEFAULT_CUSTOMER_NAME).active(true).build();
        defaultProduct = Product.builder().id(DEFAULT_PRODUCT_ID).name(DEFAULT_PRODUCT_NAME).build();
        defaultPurchase = Purchase.builder().id(DEFAULT_PURCHASE_ID).customer(defaultCustomer).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void findCustomerById() {
        //setup
        List<Customer> expectedCustomers = Collections.singletonList(defaultCustomer);

        //execution
        List<Customer> actualCustomers = customerRepo.findAllById(Collections.singleton(DEFAULT_CUSTOMER_ID));

        //validation
        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    public void findCustomersByName() {
        //setup
        List<Customer> expectedCustomers = Collections.singletonList(defaultCustomer);

        //execution
        List<Customer> actualCustomers = customerRepo.findAllByName(DEFAULT_CUSTOMER_NAME);

        //validation
        assertEquals(expectedCustomers, actualCustomers);
    }


    @Test
    public void findProductById() {
        //setup
        List<Product> expectedProducts = Collections.singletonList(defaultProduct);

        //execution
        List<Product> actualProducts = productRepo.findAllById(Collections.singleton(DEFAULT_PRODUCT_ID));

        //validation
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void findProductsByName() {
        //setup
        List<Product> expectedProducts = Collections.singletonList(defaultProduct);

        //execution
        List<Product> actualProducts = productRepo.findAllByName(DEFAULT_PRODUCT_NAME);

        //validation
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void findPurchaseExists() {

        //execution
        List<Purchase> all = purchaseRepo.findAll();

        //validation
        assertFalse(all.isEmpty());
    }

    @Test
    public void findPurchaseByCreateDate() throws ParseException {
        //setup
        List<Purchase> expectedPurchase = Collections.singletonList(defaultPurchase);

        //execution
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Purchase> all = purchaseRepo.findAllByCreationDateTimeBetween(simpleDateFormat.parse("2021-04-01"),
                simpleDateFormat.parse("2021-04-02"));

        //validation
        assertEquals(expectedPurchase, all);
    }

    @Test
    public void createCustomer() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.SAVE_PATH);
        String testCustomer = "testCustomer";
        String requestJson = OBJECT_WRITER.writeValueAsString(Customer.builder().name(testCustomer).active(false).build());

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(testCustomer)));

    }

    @Test
    public void updateCustomer() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.SAVE_PATH);
        String updatedCustomerName = "updatedCustomerName";
        defaultCustomer.setName(updatedCustomerName);
        String requestJson = OBJECT_WRITER.writeValueAsString(defaultCustomer);

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(101)))
                .andExpect(jsonPath("$.name", is(updatedCustomerName)));

    }

    @Test
    public void updateCustomerActive() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.UPDATE_ACTIVE_PATH);
        Optional<Customer> byId = customerRepo.findById(101L);
        assertTrue(byId.isPresent());
        assertTrue(byId.get().getActive());

        String requestJson = "{\"id\": 101, \"active\": false}";

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());

        Optional<Customer> byIdAfter = customerRepo.findById(101L);
        assertTrue(byIdAfter.isPresent());
        assertFalse(byIdAfter.get().getActive());

    }

    private static String buildUrl(String... paths) {
        String pathSeparator = "/";
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(paths).forEach(s -> stringBuilder.append(pathSeparator).append(s));
        return stringBuilder.toString();
    }
}
