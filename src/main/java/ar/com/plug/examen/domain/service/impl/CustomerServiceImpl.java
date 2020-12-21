package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public Customer getOne(long id) {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer add(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer modify(Customer customer) {
		customerRepository.findById(customer.getId()).orElseThrow(() -> new CustomerNotFoundException(customer.getId()));
		return customerRepository.save(customer);
	}

	@Override
	public void delete(long id) {
		customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		customerRepository.deleteById(id);
	}

}
