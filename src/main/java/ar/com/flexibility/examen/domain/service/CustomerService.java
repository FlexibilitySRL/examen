package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.rest.dto.CustomerRequestDto;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.exception.DataValidationException;

public interface CustomerService {
	public Customer findCustomer(String customerName);

	public void createCustomer(CustomerRequestDto dto);

	public void deleteCustomer(Integer customerId);

	public void updateCustomer(CustomerRequestDto dto) throws DataValidationException;
}
