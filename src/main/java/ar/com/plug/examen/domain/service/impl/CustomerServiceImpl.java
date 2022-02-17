package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        Customer customerToSave = new Customer();
        customerToSave.setDocumentNumber(customerDTO.getDocumentNumber());
        customerToSave.setName(customerDTO.getName());
        customerToSave.setLastName(customerDTO.getLastName());
        customerToSave.setEmail(customerDTO.getEmail());
        customerRepository.save(customerToSave);
    }

    @Override
    public void deleteCustomer(Long idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    @Override
    public void editCustomer(Long idCustomer, CustomerDTO customerDTO) {
        Optional<Customer> customerResult = customerRepository.findById(idCustomer);
        customerResult.get().setName(customerDTO.getName());
        customerResult.get().setLastName(customerDTO.getLastName());
        customerResult.get().setDocumentNumber(customerDTO.getDocumentNumber());
        customerResult.get().setEmail(customerDTO.getEmail());
        customerRepository.save(customerResult.get());
    }
}
