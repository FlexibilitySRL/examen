package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.repository.ICustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {



    @Autowired
    private ICustomerRepository customerRepository;


    @Override
    public CustomerDTO save(CustomerDTO customerDto) {

        return customerRepository.save(customerDto);
    }


    @Override
    public CustomerDTO update(CustomerDTO customerDto) {

        return customerRepository.update(customerDto);
    }

    /**
     * Delete a customer given an Id
     * @param customerId
     */
    @Override
    public void delete(String customerId) {
          customerRepository.delete(customerId);
    }

    @Override
    public Optional<CustomerDTO> getById(String customerId) {

        return customerRepository.getById(customerId);
    }


    @Override
    public List<CustomerDTO> getAll() {
        return (List<CustomerDTO>) customerRepository.getAll();
    }
}
