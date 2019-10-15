package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.*;
import ar.com.flexibility.examen.domain.persistence.CustomerRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class CustomerServiceImplTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkOutOrder() {

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

        ar.com.flexibility.examen.domain.model.Address address1 = ar.com.flexibility.examen.domain.model.Address.
                builder().city("city").name("name").state("state").streetAddress("street address").zipCode("1234").build();

        customerService.checkOutOrder(customer, shippingOrder.getId(), address1);

        Customer customer1 = customerService.find(customer.getId());

        assertTrue(customer1.getAccount().getShippingOrders().size() == 1);
        assertTrue(OrderStatus.APPROVED == customer1.getAccount().getShippingOrders().get(0).getStatus());
    }
}