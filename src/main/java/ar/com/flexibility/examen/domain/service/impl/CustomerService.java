package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CRUD service for {@link Customer}
 */
@Service
@Transactional
public class CustomerService extends AbstractCrudService<Customer, Long> {


	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.repository = customerRepository;
	}

}
