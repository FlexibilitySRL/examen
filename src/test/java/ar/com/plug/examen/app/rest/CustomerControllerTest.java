package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.CustomerModel;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Test
    void request_add_customer_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        CustomerModel customerModel = new CustomerModel(1L,"Customer Final", 1);
        expectedJsonResponseTransaction.setCustomerModel(customerModel);

        when(customerService.addCustomer(any(CustomerModel.class))).thenReturn(expectedJsonResponseTransaction);
        CustomerController customerController = new CustomerController(customerService);

        ResponseEntity<JsonResponseTransaction> result = customerController.addCustomer(customerModel);

        verify(customerService).addCustomer(customerModel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }

    @Test
    void request_update_customer_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        CustomerModel customerModel = new CustomerModel(1L,"Customer Final2", 1);
        expectedJsonResponseTransaction.setCustomerModel(customerModel);

        when(customerService.updateCustomer(any(CustomerModel.class))).thenReturn(expectedJsonResponseTransaction);
        CustomerController customerController = new CustomerController(customerService);

        ResponseEntity<JsonResponseTransaction> result = customerController.updateCustomer(customerModel);

        verify(customerService).updateCustomer(customerModel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }

    @Test
    void request_delete_customer_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        expectedJsonResponseTransaction.setResponseMessage("Customer: "+1L + " eliminated of system");

        when(customerService.deleteCustomer(1L)).thenReturn(expectedJsonResponseTransaction);
        CustomerController customerController = new CustomerController(customerService);

        ResponseEntity<JsonResponseTransaction> result = customerController.deleteCustomer(1L);

        verify(customerService).deleteCustomer(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }
}