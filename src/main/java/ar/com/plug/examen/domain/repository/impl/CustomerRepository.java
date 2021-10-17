package ar.com.plug.examen.domain.repository.impl;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.crud.CustomerCrudRepository;
import ar.com.plug.examen.domain.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository implements ICustomerRepository {

    @Autowired
    private CustomerCrudRepository customerCrudRepository;

    @Override
    public Customer save(Customer customer) {

        return customerCrudRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {

        return customerCrudRepository.save(customer);
    }

    @Override
    public void delete(String customerId) {

        customerCrudRepository.deleteById(customerId);
    }

    @Override
    public Optional<Customer> getById(String customerId) {

        return customerCrudRepository.findById(customerId);
    }


    @Override
    public List<Customer> getAll() {

        return (List<Customer>) customerCrudRepository.findAll();
    }
}
