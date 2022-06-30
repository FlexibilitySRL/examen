package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.domain.execptions.ChallengeException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repositories.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    public static final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<CustomerApi> findById(Long customerId) {
        return Optional.empty();
    }

    @Override
    public CustomerApi getCustomerById(Long customerId) throws ChallengeException {
        return modelMapper
                .map(customerRepository
                        .findById(customerId)
                        .orElseThrow(() -> new NotFoundException("customer not found", "customer not fount")), CustomerApi.class);
    }

    @Override
    public CustomerApi createCustomer(CustomerApi customerApi) {
        Customer customer = customerRepository.save(modelMapper.map(customerApi, Customer.class));
        log.info("The customer " + customer.getId() + " was succesfully created.");
        return modelMapper.map(customer, CustomerApi.class);
    }

    @Override
    public List<CustomerApi> listAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            log.error("The customer list is empty.");
        }
        return customers
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerApi.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeCustomer(Long customerId) throws NotFoundException {
        if (!customerRepository.existsById(customerId)) {
            log.error("The customer with the id:" + customerId + " does not exist.");
            throw new NotFoundException("", "The customer with the id:\" + id + \" does not exist.");
        }
        customerRepository.deleteById(customerId);
        log.info("The customer with the id:" + customerId + " was successfully deleted.");
    }

    @Override
    public CustomerApi updateCustomer(Long customerId, CustomerApi customerApi) throws NotFoundException {

        if (!customerRepository.existsById(customerId)) {
            log.error("The customer with the id:" + customerId + " does not exist.");
            throw new NotFoundException("", "customer with id " + customerId + " does not exist");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("", "customer with the id:" + customerId + " was not found."));

        customer.setName(customerApi.getName());
        log.info("The customer " + customer.getId() + " was successfully updated.");

        return modelMapper.map(customerRepository.save(customer), CustomerApi.class);
    }
}
