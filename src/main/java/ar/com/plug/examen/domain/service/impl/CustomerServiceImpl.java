package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;






/**
 * Implementation of the CustomerService that uses a CrudRepository
 *
 * @author julimanfre@hotmail.com
 */




@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    CustomerRepository customerRepository;
    
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public Customer create(Customer customer) {
		
		LOGGER.info("Creating Customer....");
 		
		Customer customerCreated = customerRepository.save(customer);
		
		if(customerCreated==null){

			LOGGER.error("Customer Created....");
		}else{

			LOGGER.info("Customer updated Succesfully....");
		}		
		
 		return customerCreated;
	}

	@Override
	public Customer update(Long id, Customer customer) {
		
		Customer customerUpdated = customerRepository.save(customer);
		
		if(customerUpdated==null){

			LOGGER.error("Customer updated....");
		}else{

			LOGGER.info("Customer updated Succesfully....");
		}		
		
 		return customerUpdated;

	}

	@Override
	public void delete(Long id) {
		
		LOGGER.info("Deleting Customer by Id...." + id);

		customerRepository.deleteById(id);;
	}

	@Override
	public List<Customer> getCustomers() {

		LOGGER.info("Retrieve All customers....");

 		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {

		LOGGER.info("Retrieve customer Id ...." + id);
		
		Optional<Customer> result = customerRepository.findById(id);
		
		if (!result.isPresent()){
			
			LOGGER.info(" customer found Id ...." + id);

		}else{
			
			LOGGER.error(" customer not found Id ...." + id);
		}
		
		return result;
	}

	
    
}
