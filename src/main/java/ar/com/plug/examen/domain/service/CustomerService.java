package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Customer;

public interface CustomerService {

	Customer create(Customer customer);
	Customer update(Long id, Customer customer);
	void delete(Long id);
	List<Customer> getCustomers();
	Optional<Customer> getCustomerById(Long id);
}
