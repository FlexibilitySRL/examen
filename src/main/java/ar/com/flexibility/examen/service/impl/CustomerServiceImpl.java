package ar.com.flexibility.examen.service.impl;

import ar.com.flexibility.examen.app.dto.CustomerDTO;
import ar.com.flexibility.examen.model.Customer;
import ar.com.flexibility.examen.model.repository.CustomerRepository;
import ar.com.flexibility.examen.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createCustomer(CustomerDTO customerDTO){

        customerRepository.save(mapDtoToEntity(customerDTO));
    }
    @Override
    public Customer retrieveCustomerById(Long id) {
        Optional<Customer> customer  = customerRepository.findById(id);
        if(customer.isPresent()) {
            return customer.get();
        }
        return null;
    }

    @Override
    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        customerDTO.setId(id);
        customerRepository.save(mapDtoToEntity(customerDTO));
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }


    private Customer mapDtoToEntity(CustomerDTO customerDto) {
        return modelMapper.map(customerDto,Customer.class);
    }
}
