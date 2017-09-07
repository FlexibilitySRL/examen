package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Customer;

public interface CustomerService  {

	public Customer createCustomer(Customer customer);
	
	public Customer findCustomerById(String id);
	
	public List<Customer> findAllCustomers();
	
	public Customer updateCustomer(Customer customer);
	
	public Customer deleteCustomer(Customer customer);
}