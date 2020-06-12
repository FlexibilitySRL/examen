package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.repo.CustomerRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerDAO;
    
    @Override
    public Customer create(Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public Customer read(Long id) {
        return customerDAO.findOne(id);
    }

    @Override
    public Customer update(Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerDAO.delete(id);
    }

    @Override
    public List<Customer> search(String maskname) {
        return customerDAO.findByMaskname(maskname);
    }

}
