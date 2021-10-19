package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.repository.ICustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private ICustomerRepository customerRepository;


    @Override
    public CustomerDTO save(CustomerDTO customerDto) {

        logger.info("Saving customer: " + customerDto.getName());
        return customerRepository.save(customerDto);
    }


    @Override
    public CustomerDTO update(CustomerDTO customerDto) {

        logger.info("Updating customer: " + customerDto.getName());
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

        logger.info("Find customer by id: " + customerId);
        return customerRepository.getById(customerId);
    }


    @Override
    public List<CustomerDTO> getAll() {

        logger.info("Get all customer");
        return (List<CustomerDTO>) customerRepository.getAll();

    }
}
