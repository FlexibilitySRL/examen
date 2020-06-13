package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repo.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerServiceImpl implements CustomerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    
    @Autowired
    CustomerRepository customerDAO;
    
    @Override
    public Customer create(Customer customer) {
        LOGGER.info("Creating customer");
        return customerDAO.save(customer);
    }

    @Override
    public Customer read(Long id) {
        LOGGER.info("Retrieving customer");
        return customerDAO.findOne(id);
    }

    @Override
    public Customer update(Customer customer) {
        LOGGER.info("Updating customer");
        return customerDAO.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerDAO.delete(id);
        LOGGER.info("Customer deleted");
    }

    @Override
    public List<Customer> search(String maskname) {
        return customerDAO.findByMaskname(maskname);
    }

}
