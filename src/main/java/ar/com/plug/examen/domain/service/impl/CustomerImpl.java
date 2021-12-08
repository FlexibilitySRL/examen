package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;

@Service
public class CustomerImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> consultar() {
		
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public void crear(Customer customer) {
		
		customerRepository.save(customer);
		
	}

	@Override
	public void editar(Customer customer) {
		// TODO Auto-generated method stub
		
	}

}
