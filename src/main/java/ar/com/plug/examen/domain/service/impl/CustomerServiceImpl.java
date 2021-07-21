package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repositories.ICustomerRepository;
import ar.com.plug.examen.domain.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository repository;


    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Customer save(@Valid Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        if (repository.existsById(id))
            return repository.findById(id).get();

        return null;
    }

    @Override
    public Customer update(@Valid Customer customer) {
        if (repository.existsById(customer.getId())){
            return repository.save(customer);
        }

        return null;
    }
}
