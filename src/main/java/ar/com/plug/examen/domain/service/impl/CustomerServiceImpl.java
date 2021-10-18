package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.ICustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public CustomerDTO save(CustomerDTO customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDTO update(CustomerDTO customer) {

        return customerRepository.update(customer);
    }

    @Override
    public void delete(String customerId) {
          customerRepository.delete(customerId);
    }

    @Override
    public Optional<CustomerDTO> getById(String customerId) {

        return customerRepository.getById(customerId);
    }

    @Override
    public List<CustomerDTO> getAll() {

        return (List<CustomerDTO>) customerRepository.getAll();
    }
}
