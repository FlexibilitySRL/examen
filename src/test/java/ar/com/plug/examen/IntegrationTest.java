package ar.com.plug.examen;

import ar.com.plug.examen.app.rest.CustomerController;
import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.app.rest.PurchaseController;
import ar.com.plug.examen.datasource.model.Customer;
import ar.com.plug.examen.datasource.model.Product;
import ar.com.plug.examen.datasource.model.Purchase;
import ar.com.plug.examen.datasource.repo.CustomerRepo;
import ar.com.plug.examen.datasource.repo.ProductRepo;
import ar.com.plug.examen.datasource.repo.PurchaseRepo;
import ar.com.plug.examen.domain.service.impl.ProcessPurchaseServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback
@Transactional
@ActiveProfiles(profiles = "local")
public class IntegrationTest {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    static {
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    private static final String DEFAULT_CUSTOMER_NAME = "Customer 101";
    private static final long DEFAULT_CUSTOMER_ID = 101L;
    private static final long DEFAULT_PRODUCT_ID = 201L;
    private static final String DEFAULT_PRODUCT_NAME = "Product 201";
    private static final long DEFAULT_PURCHASE_ID = 301L;

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private static final ObjectWriter OBJECT_WRITER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer().withDefaultPrettyPrinter();

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
        defaultCustomer = Customer.builder().id(DEFAULT_CUSTOMER_ID).name(DEFAULT_CUSTOMER_NAME).build();
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
        List<Purchase> all = purchaseRepo.findAllByCreationDateTimeBetween(
                ProcessPurchaseServiceImpl.simpleDateFormat.parse("2021-04-01"),
                ProcessPurchaseServiceImpl.simpleDateFormat.parse("2021-04-02"));

        //validation
        assertEquals(expectedPurchase, all);
    }

    @Test
    public void createCustomer() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.CREATE_PATH);
        final Customer testCustomer = Customer.builder().name("Test Name" + Math.random()).build();
        String requestJson = OBJECT_WRITER.writeValueAsString(testCustomer);

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", is(testCustomer.getName())))
                .andExpect(jsonPath("$.deleted", is(testCustomer.getDeleted())));

    }

    @Test
    public void createCustomerNameRequired() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.CREATE_PATH);
        final Customer testCustomer = Customer.builder().build();
        String requestJson = OBJECT_WRITER.writeValueAsString(testCustomer);

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertEquals(CustomerController.NAME_REQUIRED_MESSAGE, result.getResponse().getErrorMessage()));

    }

    @Test
    public void readCustomer() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.READ_PATH);
        final Customer testCustomer = customerRepo.save(Customer.builder().name("Test Name").build());

        final Long id = testCustomer.getId();

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(id.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testCustomer.getId().intValue())))
                .andExpect(jsonPath("$.name", is(testCustomer.getName())))
                .andExpect(jsonPath("$.deleted", is(testCustomer.getDeleted())));

    }

    @Test
    public void readCustomerIdRequired() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.READ_PATH);

        String requestJson = "";

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateCustomer() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.UPDATE_PATH);
        final Customer testCustomer = customerRepo.save(Customer.builder().name("Test Name").build());
        final Customer updatedCustomer = Customer.builder().id(testCustomer.getId()).name("differentName").build();
        String requestJson = OBJECT_WRITER.writeValueAsString(updatedCustomer);

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedCustomer.getId().intValue())))
                .andExpect(jsonPath("$.name", is(updatedCustomer.getName())))
                .andExpect(jsonPath("$.deleted", is(updatedCustomer.getDeleted())));

    }

    @Test
    public void updateCustomerOneValue() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.UPDATE_PATH);
        final Customer testCustomer = customerRepo.save(Customer.builder().name("Test Name").build());
        final Customer updatedCustomer = Customer.builder().id(testCustomer.getId()).deleted(new Date()).build();
        String requestJson = OBJECT_WRITER.writeValueAsString(updatedCustomer);

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedCustomer.getId().intValue())))
                .andExpect(jsonPath("$.name", is(testCustomer.getName())))
                .andExpect(jsonPath("$.deleted", is(df.format(updatedCustomer.getDeleted()))));

    }

    @Test
    public void deleteCustomer() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.DELETE_PATH);
        final Customer testCustomer = customerRepo.save(Customer.builder().name("Test Name").build());

        final Long id = testCustomer.getId();

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(id.toString()))
                .andExpect(status().isOk());

        Optional<Customer> byIdAfter = customerRepo.findById(id);
        assertTrue(byIdAfter.isPresent());
        assertNotNull(byIdAfter.get().getDeleted());

    }

    @Test
    public void deleteCustomerIdRequired() throws Exception {
        //setup
        String url = buildUrl(CustomerController.ROOT_PATH, CustomerController.DELETE_PATH);

        String requestJson = "{}";

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void createProduct() throws Exception {
        //setup
        String url = buildUrl(ProductController.ROOT_PATH, CustomerController.CREATE_PATH);
        final Product testProduct = Product.builder().name("Test Name" + Math.random()).build();
        String requestJson = OBJECT_WRITER.writeValueAsString(testProduct);

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", is(testProduct.getName())))
                .andExpect(jsonPath("$.deleted", is(testProduct.getDeleted())));

    }

    @Test
    public void readPurchase() throws Exception {
        //setup
        String url = buildUrl(PurchaseController.ROOT_PATH, PurchaseController.READ_PATH);
        final Customer testCustomer = customerRepo.save(Customer.builder().name("Test Name").build());
        final Purchase saved = purchaseRepo.save(Purchase.builder().customer(testCustomer).build());
        final Long id = saved.getId();

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(id.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(saved.getId().intValue())))
                .andExpect(jsonPath("$.approved", is(saved.isApproved())));

    }

    @Test
    public void approvePurchase() throws Exception {
        //setup
        String url = buildUrl(PurchaseController.ROOT_PATH, PurchaseController.APPROVE_PATH);
        final Customer testCustomer = customerRepo.save(Customer.builder().name("Test Name").build());
        final Purchase saved = purchaseRepo.save(Purchase.builder().approved(false).customer(testCustomer).build());
        final Long id = saved.getId();

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(id.toString()))
                .andExpect(status().isOk());

        Optional<Purchase> byIdAfter = purchaseRepo.findById(id);
        assertTrue(byIdAfter.isPresent());
        assertTrue(byIdAfter.get().isApproved());
    }

    @Test
    public void historyPurchase() throws Exception {
        //setup
        String url = buildUrl(PurchaseController.ROOT_PATH, PurchaseController.HISTORY_PATH);
        final Product testProduct = productRepo.save(Product.builder().name("Test Product").build());
        final Customer testCustomer = customerRepo.save(Customer.builder().name("Test Customer").build());

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        final Date creationTimeStart = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, +2);
        final Date creationTimeEnd = calendar.getTime();
        final String dateStart = ProcessPurchaseServiceImpl.simpleDateFormat.format(creationTimeStart);
        final String dateEnd = ProcessPurchaseServiceImpl.simpleDateFormat.format(creationTimeEnd);

        final Purchase saved = purchaseRepo.save(
                Purchase.builder()
                        .approved(false)
                        .creationDateTime(creationTimeStart)
                        .customer(testCustomer)
                        .products(Collections.singletonList(testProduct))
                        .build());

        String requestJson = String.format("{\"creationTimeStart\": \"%s\", \"creationTimeEnd\": \"%s\"}", dateStart, dateEnd);

        //execution and validation
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(saved.getId().intValue())))
                .andExpect(jsonPath("$[0].approved", is(saved.isApproved())))
                .andExpect(jsonPath("$[0].id", is(saved.getId().intValue())))
                .andExpect(jsonPath("$[0].customer.id", is(saved.getCustomer().getId().intValue())))
                .andExpect(jsonPath("$[0].products[0].id", is(saved.getProducts().get(0).getId().intValue())));

    }

    private static String buildUrl(String... paths) {
        String pathSeparator = "/";
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(paths).forEach(s -> stringBuilder.append(pathSeparator).append(s));
        return stringBuilder.toString();
    }
}
