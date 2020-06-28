package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();
	
	Customer getCustomerById(Long customerId) ;
	
	Customer save(Customer customer);
	
	void deleteCustomer(Long customerId) ;
	
	Customer getCustomerByEmail(String customerEmail) ;

	boolean exists(Long customerId);
	
}
