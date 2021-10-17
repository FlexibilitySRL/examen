package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.impl.CustomerRepository;
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
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {

        return customerRepository.update(customer);
    }

    @Override
    public void delete(String customerId) {
          customerRepository.delete(customerId);
    }

    @Override
    public Optional<Customer> getById(String customerId) {

        return customerRepository.getById(customerId);
    }

    @Override
    public List<Customer> getAll() {

        return (List<Customer>) customerRepository.getAll();
    }
}
