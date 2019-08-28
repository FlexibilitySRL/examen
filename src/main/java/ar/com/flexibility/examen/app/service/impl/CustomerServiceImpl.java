package ar.com.flexibility.examen.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import ar.com.flexibility.examen.app.api.dto.CustomerDTO;
import ar.com.flexibility.examen.app.utils.EmailValidator;
import ar.com.flexibility.examen.app.utils.HashingPassword;
import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService, UserDetailsService { // UserDetailsService Spring Security

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Page<Customer> getCustomers(Pageable pageable) {
		return customerRepository.findAll(new PageRequest(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public Customer getCustomerById(Long customerId) throws EntityNotFoundException {

		Customer customer = customerRepository.findOne(customerId);
		if (customer == null) {
			throw new EntityNotFoundException("Customer with id: " + customerId + " does not exists");
		}
		return customer;
	}

	@Override
	public Customer getCustomerByEmail(String customerEmail) throws EntityNotFoundException {

		Customer customer = customerRepository.findByEmail(customerEmail);
		if (customer == null) {
			throw new EntityNotFoundException("Customer with email: " + customerEmail + " does not exists");
		}
		return customer;
	}

	@Override
	public Customer getCustomerByEmailAndPassword(String customerEmail, String customerPassword) throws BadCredentialsException {

		if (EmailValidator.validate(customerEmail)) {
			try {
				Customer customer = getCustomerByEmail(customerEmail);
				if (HashingPassword.checkPassword(customerPassword, customer.getPassword())) {
					return customer;
				} else {
					throw new BadCredentialsException("Bad credentials");
				}
			} catch (EntityNotFoundException e) {
				throw new BadCredentialsException("Bad credentials");
			}
		} else {
			throw new IllegalArgumentException("The email: " + customerEmail + " is not valid");
		}
	}

	@Override
	public Customer createCustomer(Customer customer) throws EntityExistsException {
		if (EmailValidator.validate(customer.getEmail())) {
			Customer tempCustomer = customerRepository.findByEmail(customer.getEmail());
			if (tempCustomer == null) {
				for (Address address : customer.getAddresses()) {
					address.setCustomer(customer);
				}
				customer.setOrders(new ArrayList<Order>());
				customer.setPassword(HashingPassword.hashPassword(customer.getPassword()));
				tempCustomer = customerRepository.save(customer);
				return tempCustomer;
			} else {
				throw new EntityExistsException("The email: " + customer.getEmail() + " already exists");
			}
		} else {
			throw new IllegalArgumentException("The email: " + customer.getEmail() + " is not valid");
		}
	}

	@Override
	public Customer updateCustomer(CustomerDTO customerDTO, Long customerId) throws EntityNotFoundException, EntityExistsException {

		Customer customer = this.getCustomerById(customerId); // the user must exists

		if (customerDTO.getEmail() != null) {
			if (EmailValidator.validate(customer.getEmail())) {
				Customer cus = customerRepository.findByEmail(customerDTO.getEmail());
				// the new email is available or the email didn't change
				if (cus == null || cus.getEmail().equals(customer.getEmail())) {
					customer.setEmail(customerDTO.getEmail());
				} else {
					throw new EntityExistsException("The email: " + customer.getEmail() + " already exists");
				}
			} else {
				throw new IllegalArgumentException("The email: " + customer.getEmail() + " is not valid");
			}
		}
		if (customerDTO.getName() != null) {
			customer.setName(customerDTO.getName());
		}
		if (customerDTO.getLastname() != null) {
			customer.setLastname(customerDTO.getLastname());
		}
		if (customerDTO.getMobile() != null) {
			customer.setMobile(customerDTO.getMobile());
		}
		if (customerDTO.getCurrentPassword() != null && customerDTO.getNewPassword() != null) {
			if (HashingPassword.checkPassword(customerDTO.getCurrentPassword(), customer.getPassword())) {
				customer.setPassword(HashingPassword.hashPassword(customerDTO.getNewPassword()));
			}
		}

		customer = customerRepository.save(customer);

		return customer;
	}

	@Override
	public Customer updateCustomerEmail(String email, Long customerId) throws EntityNotFoundException, EntityExistsException {

		Customer customer = this.getCustomerById(customerId); // the user must exists
		Customer cus = customerRepository.findByEmail(email);

		// the new email is available
		if (cus == null) {
			customer.setEmail(email);
			cus = customerRepository.save(customer);
		} else {
			throw new EntityExistsException("The email: " + email + " already exists");
		}
		return cus;
	}

	/**
	 * Spring Security
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(username);
		if (customer == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

}
