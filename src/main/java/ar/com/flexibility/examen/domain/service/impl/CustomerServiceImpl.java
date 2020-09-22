package ar.com.flexibility.examen.domain.service.impl;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dto.CustomerDTO;
import ar.com.flexibility.examen.domain.enums.CustomerStatus;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		Customer customer = customerRepository.findByDocument(customerDTO.getDocument());
		if (Objects.nonNull(customer)) {
			return modelMapper.map(customer,CustomerDTO.class);
		}
		customerDTO.setStatus(CustomerStatus.CREATED);
		customerRepository.save(modelMapper.map(customerDTO,Customer.class));
		return customerDTO;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customer) {
		Customer customerDB = customerRepository.findOne(customer.getId());
		if (Objects.isNull(customerDB)) {
			return null;
		}
		customerDB.setFirstName(customer.getFirstName());
		customerDB.setLastName(customer.getLastName());
		customerDB.setEmail(customer.getEmail());
		customerDB =  customerRepository.save(customerDB);
		return modelMapper.map(customer,CustomerDTO.class);
	}

	@Override
	public CustomerDTO deleteCustomer(Long id) {
		Customer customerDB = customerRepository.findOne(id);
		if (Objects.isNull(customerDB)) {
			return null;
		}
		customerDB.setStatus(CustomerStatus.DELETED);
		customerDB = customerRepository.save(customerDB);
		return modelMapper.map(customerDB,CustomerDTO.class);
	}

	@Override
	public CustomerDTO getCustomer(Long id) {
		Customer customerDB = customerRepository.findOne(id);
        return Objects.nonNull(customerDB) ?  modelMapper.map(customerDB,CustomerDTO.class) : null;
    }

}
