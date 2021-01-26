package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomer();

	public Customer getCustomerById(Long id);

	public void deleteCustomer(Long id);

	public Customer create(Customer customer);
	
	public Customer update(Customer customer);
}
