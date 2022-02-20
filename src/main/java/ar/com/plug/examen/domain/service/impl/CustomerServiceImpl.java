package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.exception.CustomerNotFoundException;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.exception.ProductParamException;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.domain.util.LoggerExample;
import ar.com.plug.examen.domain.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.com.plug.examen.domain.constants.ErrorConstants.INVALID_PRODUCT_FIELD;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
    private static final String DOCUMENT_NUMBER = "document number";
    private static final String NAME = "name";
    private static final String LAST_NAME = "last name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        try {
            validateInputData(customerDTO);
            Customer customerToSave = new Customer();
            customerToSave.setDocumentNumber(customerDTO.getDocumentNumber());
            customerToSave.setName(customerDTO.getName());
            customerToSave.setLastName(customerDTO.getLastName());
            customerToSave.setEmail(customerDTO.getEmail());
            customerToSave.setPhone(customerDTO.getPhone());
            customerRepository.save(customerToSave);
        } catch (ExceptionInInitializerError ex) {
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        } finally {

        }
    }

    @Override
    public void deleteCustomer(Long idCustomer) {
        try {
            existsCustomer(idCustomer);
            customerRepository.deleteById(idCustomer);
        } catch (ExceptionInInitializerError ex) {
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        } finally {

        }
    }

    @Override
    public void editCustomer(Long idCustomer, CustomerDTO customerDTO) {
        try {
            validateInputData(customerDTO);
            Optional<Customer> customerResult = existsCustomer(idCustomer);
            customerResult.get().setName(customerDTO.getName());
            customerResult.get().setLastName(customerDTO.getLastName());
            customerResult.get().setDocumentNumber(customerDTO.getDocumentNumber());
            customerResult.get().setEmail(customerDTO.getEmail());
            customerResult.get().setPhone(customerDTO.getPhone());
            customerResult.get().setUpdateDate(LocalDateTime.now());
            customerRepository.save(customerResult.get());
        } catch (ExceptionInInitializerError ex) {
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        } finally {

        }
    }

    private void validateInputData(CustomerDTO customerDTO) {
        List<String> param = new ArrayList<>();

        if (Util.isBlank(customerDTO.getName())) {
            param.add(NAME);
        }
        if (Util.isBlank(customerDTO.getLastName())) {
            param.add(LAST_NAME);
        }
        if (Util.isBlank(customerDTO.getDocumentNumber())) {
            param.add(DOCUMENT_NUMBER);
        }
        if (Util.isBlank(customerDTO.getEmail())) {
            param.add(EMAIL);
        }
        if (Util.isBlank(customerDTO.getPhone())) {
            param.add(PHONE);
        }
        if (!param.isEmpty()) {
            throw new ProductParamException(INVALID_PRODUCT_FIELD, param);
        }
    }

    private Optional<Customer> existsCustomer(long idCustomer) {
        Optional<Customer> customerResult = customerRepository.findById(idCustomer);
        if (!customerResult.isPresent()) {
            throw new CustomerNotFoundException();
        }
        return customerResult;
    }
}
