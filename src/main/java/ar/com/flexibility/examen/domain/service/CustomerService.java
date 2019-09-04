package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.model.Customer;

public interface CustomerService {

	public List<CustomerDTO> findAll();

	public CustomerDTO findById(Long id);

	public CustomerDTO save(CustomerDTO dto);

	public Boolean delete(Long id);

	public CustomerDTO entityToDto(Customer entity);

	public Customer dtoToEntity(CustomerDTO dto);

}
