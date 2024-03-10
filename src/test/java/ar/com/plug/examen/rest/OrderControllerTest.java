package ar.com.plug.examen.rest;

import ar.com.plug.examen.app.rest.OrderController;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Order;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void createOrder() throws Exception {
        // Create a sample Order object
        Order testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setClient(new Customer()); // Set required fields
        testOrder.setProducts(Collections.singletonList(new Product()));

        // Mock service behavior
        when(orderService.createOrder(any())).thenReturn(testOrder);

        // Perform the request
        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": 1, \n" +
                                "  \"client\": {  \n" +
                                "    \"id\": 2,\n" +
                                "    \"firstName\": \"John\",\n" +
                                "    \"lastName\": \"Doe\",\n" +
                                "    \"email\": \"john.doe@example.com\"\n" +
                                "  },\n" +
                                "  \"products\": [ \n" +
                                "    {\n" +
                                "      \"id\": 3,\n" +
                                "      \"name\": \"Product 1\",\n" +
                                "      \"price\": 9.99\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"id\": 4,\n" +
                                "      \"name\": \"Product 2\",\n" +
                                "      \"price\": 14.50\n" +
                                "    }\n" +
                                "  ],\n" +
                                "  \"orderDate\": \"2024-03-10T12:00:00\", \n" +
                                "  \"total\": null \n" +
                                "}\n"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L)); // Assert response content

        // Verify service interaction
        verify(orderService).createOrder(any());
    }
}

