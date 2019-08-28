package ar.com.flexibility.examen.app.service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import ar.com.flexibility.examen.app.api.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;

public interface CustomerService {

	Iterable<Customer> getAllCustomers();

	Page<Customer> getCustomers(Pageable pageable);

	Customer getCustomerById(Long customerId) throws EntityNotFoundException;

	Customer getCustomerByEmail(String customerEmail) throws EntityNotFoundException;

	Customer getCustomerByEmailAndPassword(String customerEmail, String customerPassword) throws BadCredentialsException;

	Customer createCustomer(Customer customer) throws EntityExistsException;

	Customer updateCustomer(CustomerDTO customerDTO, Long customerId) throws EntityNotFoundException, EntityExistsException;

	Customer updateCustomerEmail(String email, Long customerId) throws EntityNotFoundException, EntityExistsException;

}
