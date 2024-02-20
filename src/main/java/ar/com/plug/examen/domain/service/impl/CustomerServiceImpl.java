package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.CustomerException;
import ar.com.plug.examen.domain.model.Cart;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer addCustomer(Customer customer) throws CustomerException {
        Cart cart = new Cart();
        customer.setCart(cart);
        cart.setCustomer(customer);
        Customer c = customerRepository.save(customer);

        if (c != null) {
            return c;
        } else {
            throw new CustomerException("customer not added");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerException {
        Customer c = customerRepository.findById(customer.getCId()).orElseThrow(() -> new CustomerException("Customer not found"));
        if (c != null) {
            customerRepository.save(customer);
        }
        return c;
    }

    @Override
    public Customer remove(Integer customerId) throws CustomerException {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isPresent()) {
            Customer c = opt.get();
            customerRepository.delete(c);
            return c;
        } else {
            throw new CustomerException("Customer not found with cid - " + customerId);
        }

    }

    @Override
    public List<Customer> viewAllCustomer() throws CustomerException {
        List<Customer> customers = customerRepository.findAll();
        if (customers.size() > 0) {
            return customers;
        } else {
            throw new CustomerException("customer not found");
        }
    }
}
