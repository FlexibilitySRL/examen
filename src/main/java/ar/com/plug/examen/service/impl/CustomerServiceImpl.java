package ar.com.plug.examen.service.impl;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.repository.CustomerRepository;
import ar.com.plug.examen.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        log.info("::createCustomer:: {}", customer.getFirstName());
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        log.info("::getCustomerById:: {}", id);
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

