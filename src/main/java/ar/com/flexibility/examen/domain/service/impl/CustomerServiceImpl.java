package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.CustomerService#create(ar.com.flexibility.examen.domain.model.Customer)
	 */
	@Override
	public Customer create(Customer customer) {
		
		return customerRepository.save(customer);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.CustomerService#update(java.lang.Long)
	 */
	@Override
	public Customer update(Customer customer) {
		
		if (customerRepository.findOne(customer.getId()) != null) {
			return customerRepository.saveAndFlush(customer);
		} else {
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.CustomerService#findById(java.lang.Long)
	 */
	@Override
	public Customer findById(Long idCustomer) {
		
		return customerRepository.findOne(idCustomer);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.CustomerService#findAll()
	 */
	@Override
	public List<Customer> findAll() {
		
		return customerRepository.findAll();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.CustomerService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long idCustomer) {
		
		customerRepository.delete(idCustomer);
	}

}
