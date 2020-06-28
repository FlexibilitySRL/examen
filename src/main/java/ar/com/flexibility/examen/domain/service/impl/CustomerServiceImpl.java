package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer getCustomerById(Long customerId) {
		Customer customer = customerRepository.findOne(customerId);
		if (customer == null) {
			throw new EntityNotFoundException("Customer with id: " + customerId + " does not exists");
		}
		return customer;
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.delete(customerId);
	}

	@Override
	@Transactional(readOnly = true)
	public Customer getCustomerByEmail(String customerEmail) {
		Customer customer = customerRepository.findByEmail(customerEmail);
		if (customer == null) {
			throw new EntityNotFoundException("Customer with email: " + customerEmail + " does not exists");
		}
		return customer;
	}

	@Override
	public boolean exists(Long customerId) {
		return customerRepository.exists(customerId);
	}

}
