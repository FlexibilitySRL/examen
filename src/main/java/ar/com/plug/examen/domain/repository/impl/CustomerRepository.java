package ar.com.plug.examen.domain.repository.impl;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.mapper.CustomerMapper;
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

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {

        Customer customer = customerMapper.toCustomer(customerDTO);
        return   customerMapper.toCustomerDto(customerCrudRepository.save(customer));
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {

        Customer customer = customerMapper.toCustomer(customerDTO);
        return   customerMapper.toCustomerDto(customerCrudRepository.save(customer));
    }

    @Override
    public void delete(String customerId) {

        customerCrudRepository.deleteById(customerId);
    }

    @Override
    public Optional<CustomerDTO> getById(String customerId) {

        return customerCrudRepository.findById(customerId)
                .map(customer -> customerMapper.toCustomerDto(customer));
    }


    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> customers = (List<Customer>) customerCrudRepository.findAll();
        return customerMapper.toListCustomerDto(customers);

    }
}
