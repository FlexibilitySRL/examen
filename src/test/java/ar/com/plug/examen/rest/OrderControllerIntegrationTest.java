package ar.com.plug.examen.rest;

import ar.com.plug.examen.app.rest.OrderController;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.OrderShopping;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.service.CustomerService;
import ar.com.plug.examen.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderControllerIntegrationTest {

    @Autowired
    OrderController orderController;

    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

    @Test
    void when1IsCreated_then1AreExpected() {

        //Given
        OrderShopping mockOrder = new OrderShopping();
        mockOrder.setId(1L); // Set a sample ID

        Customer mockCustomer = new Customer();
        mockCustomer.setFirstName("John"); // Add basic customer details
        mockCustomer.setLastName("Doe");
        mockCustomer.setEmail("john.doe@example.com");
        mockCustomer = customerService.createCustomer(mockCustomer);
        mockOrder.setClient(mockCustomer);

        List<Product> mockProducts = new ArrayList<>();
        // Add sample products
        Product mockProduct1 = new Product();
        mockProduct1.setName("Product 1");
        mockProduct1.setPrice(new BigDecimal("9.99"));
        mockProduct1 = productService.createProduct(mockProduct1);
        mockProducts.add(mockProduct1);

        Product mockProduct2 = new Product();
        mockProduct2.setName("Product 2");
        mockProduct2.setPrice(new BigDecimal("14.50"));
        mockProduct2 = productService.createProduct(mockProduct2);
        mockProducts.add(mockProduct2);

        mockOrder.setProducts(mockProducts);

        //When
        //I create a Order
        orderController.createOrder(mockOrder);

        //Then
        ResponseEntity<List<OrderShopping>> allOrders = orderController.getAllOrders();

        Assertions.assertEquals(HttpStatus.OK, allOrders.getStatusCode());
        Assertions.assertEquals(1, allOrders.getBody().size());

    }
}
