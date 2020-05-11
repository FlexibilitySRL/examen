package ar.com.flexibility.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repositories.ICustomerRepository;

@Service
public class ProcessCustomerServiceImp  extends ProcessGenericEntityService<Customer>{
	
	@Autowired
	private ICustomerRepository repository;

	@Override
	public JpaRepository<Customer, Long> getRepository() {
		return repository;
	}

	@Override
	public Class<ProcessCustomerServiceImp> getClazz() {
		return ProcessCustomerServiceImp.class;
	}

	@Override
	public String getEntityName() {
		return "Customer";
	}
	
	@Override
	public Customer update(Customer customer){
		
		if(repository.exists(customer.getId())){
			customer = super.update(customer);
		}else{
			logger.info(String.format("update no existe ID: %s", customer.getId()));
		}
		
		return customer;		
	}
	
}
