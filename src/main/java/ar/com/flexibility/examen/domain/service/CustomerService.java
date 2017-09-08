package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Customer;

public interface CustomerService  {

	public Customer createCustomer(Customer customer);
	
	public Customer findCustomerById(Long id);
	
	public List<Customer> findAllCustomers();
	
	public Customer updateCustomer(Customer customer);
	
	public void deleteCustomer(Customer customer) throws Exception;
}