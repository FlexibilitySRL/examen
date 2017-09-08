package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer findCustomerById(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.exists(customer.getId()) ? customerRepository.save(customer) : null;
		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}
}