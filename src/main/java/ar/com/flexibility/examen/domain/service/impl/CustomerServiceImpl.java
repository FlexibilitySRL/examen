package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.*;
import ar.com.flexibility.examen.domain.persistence.CustomerRepository;
import ar.com.flexibility.examen.domain.persistence.ProductRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    @Override
    public void update(Long id, Customer customer) {
        Customer entity = customerRepository.findOne(id);

        if (entity == null) {
            return;
        }

        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setPhone(customer.getPhone());
        customerRepository.save(entity);
    }

    @Override
    public Customer find(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    @Override
    public void addProduct(Long id, Product product, ar.com.flexibility.examen.app.api.Product body) {
        Customer customer = customerRepository.findOne(id);

        LineItem lineItem = LineItem.builder().
                quantity(body.getQuantity()).price(body.getPrice()).product(product).build();

        customer.addItem(lineItem);
    }

    @Override
    public List<ShippingOrder> findAllOrders(Long id) {
        Customer customer = customerRepository.findOne(id);

        return customer.getAccount().getShippingOrders();
    }

    @Override
    public void checkOutOrder(Customer customer, Long order_id, Address address) {

        Optional<ShippingOrder> shippingOrder = customer.getAccount().getShippingOrders().stream().filter(shippingOrder1 -> shippingOrder1.getId().equals(order_id)).findFirst();

        shippingOrder.get().setStatus(OrderStatus.APPROVED);
        //TODO: add logic to totalize
        shippingOrder.get().setTotal(BigDecimal.TEN);
        shippingOrder.get().setShipTo(address);


        customerRepository.save(customer);

    }
}
