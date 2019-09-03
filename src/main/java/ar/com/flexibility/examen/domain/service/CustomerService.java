package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.dto.CustomerDTO;

public interface CustomerService {

	public List<CustomerDTO> findAll();

	public CustomerDTO findById(Long id);

	public CustomerDTO save(CustomerDTO dto);

	public Boolean delete(Long id);

	public CustomerDTO entityToDto(Customer customer);

	public Customer dtoToEntity(CustomerDTO customer);

}
