package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.dto.CustomerDto;
import ar.com.flexibility.examen.domain.model.dto.OrderDto;
import ar.com.flexibility.examen.domain.service.CustomerService;
import ar.com.flexibility.examen.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper mapper;

    /**
     * Creates a new customer and its account.
     *
     * @param customer the customer to create.
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Void> addCustomer(@RequestBody ar.com.flexibility.examen.app.api.Customer customer) {
        log.info("Entering to addCustomer {} ", customer);
        Customer entity = mapper.map(customer, Customer.class);
        customerService.save(entity);
        log.info("Leaving addCustomer ...");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieves all customers.
     *
     * @return a customer list.
     */
    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        log.info("Entering findAllCustomers...");
        List<CustomerDto> customerDtos = customerService.findAll().stream().map(customer -> mapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
        log.info("Leaving findAllCustomers...");
        return new ResponseEntity(customerDtos, HttpStatus.OK);
    }

    /**
     * Modifies a customer.
     *
     * @param id       the customer's id.
     * @param customer the request body.
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long id, @RequestBody ar.com.flexibility.examen.app.api.Customer customer) {
        log.info("Entering updateCustomer id = {},  {}", id, customer);
        Customer entity = mapper.map(customer, Customer.class);
        if (customerService.find(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.update(id, entity);
        log.info("Leaving updateCustomer...");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes a customer.
     *
     * @param id the customer's id.
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        log.info("Entering deleteCustomer {} ", id);

        if (customerService.find(id) != null) {
            customerService.delete(id);
        }
        log.info("Entering deleteCustomer...");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add items to a given customer.
     *
     * @param id        the customer's id.
     * @param productId the product id.
     * @param body      the request body.
     * @return
     */
    @PostMapping("/{id}/products/{product_id}")
    public ResponseEntity<Void> addProducts(@PathVariable("id") Long id, @PathVariable("product_id") Long productId, @RequestBody ar.com.flexibility.examen.app.api.Product body) {
        log.info("Entering to addProducts {} ", body);

        Product product = productService.find(productId);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.addProduct(id, product, body);
        log.info("Leaving addProducts ...");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<CustomerDto>> findAllOrders(@PathVariable("id") Long id) {
        log.info("Entering findAllOrders...");
        List<OrderDto> orderDtos = customerService.findAllOrders(id).stream().map(o -> mapper.map(o, OrderDto.class))
                .collect(Collectors.toList());
        log.info("Leaving findAllOrders...");
        return new ResponseEntity(orderDtos, HttpStatus.OK);
    }

    /**
     * aproves an Order.
     *
     * @param id       the customer's id.
     * @param order_id the order's id.
     * @param body     the body's request.
     * @return
     */
    @PutMapping("/{id}/orders/{order_id}/checkout")
    public ResponseEntity<Void> checkoutOrder(@PathVariable("id") Long id, @PathVariable("order_id") Long order_id, @RequestBody ar.com.flexibility.examen.app.api.Address body) {
        log.info("Entering checkoutOrder...");

        Customer customer = customerService.find(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Address address = mapper.map(body, Address.class);

        customerService.checkOutOrder(customer, order_id, address);
        log.info("Leaving checkoutOrder...");
        return new ResponseEntity(HttpStatus.OK);
    }
}
