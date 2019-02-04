package ar.com.flexibility.examen.domain.service;

import java.util.List;
import ar.com.flexibility.examen.domain.model.Customer;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
public interface CustomerService {
	
	/**
	 * Create a Customer
	 * 
	 * @param customer
	 * @return Customer
	 */
	public Customer create(Customer customer);
	
	/**
	 * Update a Customer
	 * 
	 * @param customer
	 * @return Customer
	 */
	public Customer update(Customer customer);
	
	/**
	 * Returns a Customer by ID
	 * 
	 * @param idCustomer
	 * @return Customer
	 */
	public Customer findById(Long idCustomer);
	
	/**
	 * Returns a list of all customers
	 * 
	 * @return List<Customer>
	 */
	public List<Customer> findAll();
	
	/**
	 * Delete a customer by ID
	 * 
	 * @param idCustomer
	 */
	public void deleteById(Long idCustomer);
}
