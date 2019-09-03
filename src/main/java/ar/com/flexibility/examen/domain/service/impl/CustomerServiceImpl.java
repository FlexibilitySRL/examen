package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public List<CustomerDTO> findAll() {
		List<CustomerDTO> dtos = new ArrayList<CustomerDTO>();
		for (Customer entity : repository.findAll()) {
			dtos.add(this.entityToDto(entity));
		}
		return dtos;
	}

	@Override
	public CustomerDTO findById(Long id) {
		Customer entity = repository.findOne(id);
		if (entity == null) {
			throw new RuntimeException();
		}
		return this.entityToDto(entity);
	}

	@Override
	public CustomerDTO save(CustomerDTO dto) {
		return this.entityToDto(repository.save(this.dtoToEntity(dto)));
	}

	@Override
	public Boolean delete(Long id) {
		Boolean deleted = Boolean.FALSE;
		if (id != null) {
			Customer entity = repository.findOne(id);
			if (entity != null) {
				repository.delete(entity);
				deleted = true;
			} else {
				throw new RuntimeException();
			}
		}
		return deleted;
	}

	@Override
	public CustomerDTO entityToDto(Customer entity) {
		CustomerDTO dto = new CustomerDTO();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public Customer dtoToEntity(CustomerDTO dto) {
		Customer entity = new Customer();
		validateCustomerDto(dto);
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private void validateCustomerDto(CustomerDTO dto) {
		if (dto.getId() != null) {
			this.findById(dto.getId());
		}
		if (dto.getCuit() == null) {
			throw new RuntimeException();
		}
	}

}
