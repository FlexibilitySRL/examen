package ar.com.plug.examen.domain.service.controller;

import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.rest.CustomerController;
import ar.com.plug.examen.app.rest.responses.ChallengeResponse;
import ar.com.plug.examen.domain.execptions.ChallengeException;
import ar.com.plug.examen.domain.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    public static final CustomerApi CUSTOMER_API = new CustomerApi();
    public static final Long CUSTOMER_ID = 1L;
    private static final String SUCCESS_STATUS = "Success";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String CREATE_CODE = "201 CREATED";
    private static final String OK = "OK";
    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController customerController;

    @Before
    public void init() throws ChallengeException {
        MockitoAnnotations.initMocks(this);
        CUSTOMER_API.setId(CUSTOMER_ID);
        CUSTOMER_API.setName("EJEMPLO");
    }

    @Test
    public void getCustomerById() throws ChallengeException {
        when(customerService.getCustomerById(1L)).thenReturn(CUSTOMER_API);
        final ChallengeResponse<CustomerApi> response = customerController.getCustomerById(CUSTOMER_ID);
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), CUSTOMER_API);
    }

    @Test
    public void getCreateCustomer() throws ChallengeException {
        when(customerService.createCustomer(CUSTOMER_API)).thenReturn(CUSTOMER_API);
        final ChallengeResponse<CustomerApi> response = customerController.createCustomer(CUSTOMER_API);
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), CREATE_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), CUSTOMER_API);
    }

    @Test
    public void getAllCustomer() throws ChallengeException {
        List<CustomerApi> customerApiLis = new ArrayList<>();
        customerApiLis.add(CUSTOMER_API);
        when(customerService.listAllCustomers()).thenReturn(customerApiLis);
        final ChallengeResponse<List<CustomerApi>> response = customerController.listAllCustomers();
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), customerApiLis);
    }
}
