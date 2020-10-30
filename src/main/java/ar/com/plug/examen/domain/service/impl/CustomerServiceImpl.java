package ar.com.plug.examen.domain.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;

@Service
public class CustomerServiceImpl {
	@Autowired
	CustomerRepository customerRepo;

	public Customer createCustomer(@Valid Customer entity) {

		return customerRepo.save(entity);

	}

}
