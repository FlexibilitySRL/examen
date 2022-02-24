package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.enums.Result;
import ar.com.plug.examen.domain.exception.*;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.LogTransation;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.LogTransationRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.domain.util.LoggerExample;
import ar.com.plug.examen.domain.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.com.plug.examen.domain.constants.ErrorConstants.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
    private static final String DOCUMENT_NUMBER = "document number";
    private static final String NAME = "name";
    private static final String LAST_NAME = "last name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ACTION_SAVE = "guardar cliente";
    private static final String ACTION_EMPTY_DATA = "editar cliente o eliminar cliente";
    private static final String VALIDATE_DATA = "validación datos cliente";
    private static final String ACTION_DELETE = "eliminar cliente";
    private static final String ACTION_EDIT = "editar cliente";
    public static final String ERROR_ID_CUSTOMER = "Debe eliminar primero de compras el vendedor con id ";
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LogTransationRepository logTransationRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    /**
     * Method with logical to save a customer
     *
     * @param customerDTO: Object type dto with information to save
     */
    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        try {
            validateInputData(customerDTO);
            existsDocumentNumber(customerDTO.getDocumentNumber());
            Customer customerToSave = new Customer();
            customerToSave.setDocumentNumber(customerDTO.getDocumentNumber());
            customerToSave.setName(customerDTO.getName());
            customerToSave.setLastName(customerDTO.getLastName());
            customerToSave.setEmail(customerDTO.getEmail());
            customerToSave.setPhone(customerDTO.getPhone());
            Customer customerSave = customerRepository.saveAndFlush(customerToSave);

            StringBuilder description = new StringBuilder();
            description.append(SAVE_SUCCESS);
            description.append(" ");
            description.append(customerSave.getIdCustomer());
            createLog(ACTION_SAVE, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_SAVE, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(Long idCustomer) {
        try {
            existsCustomer(idCustomer);
            existsRelationCustomer(idCustomer);
            customerRepository.deleteById(idCustomer);
            StringBuilder description = new StringBuilder();
            description.append(DELETE_SUCCESS);
            description.append(" ");
            description.append(idCustomer);
            createLog(ACTION_DELETE, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_DELETE, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
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
            customerRepository.saveAndFlush(customerResult.get());
            StringBuilder description = new StringBuilder();
            description.append(EDIT_SUCCESS);
            description.append(" ");
            description.append(idCustomer);
            createLog(ACTION_EDIT, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_EDIT, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void validateInputData(CustomerDTO customerDTO) {
        boolean isError = false;
        if (Util.isBlank(customerDTO.getName())) {
            isError = true;
            throw new CustomerParamException(NAME);
        }
        if (Util.isBlank(customerDTO.getLastName())) {
            isError = true;
            throw new CustomerParamException(LAST_NAME);
        }
        if (Util.isBlank(customerDTO.getDocumentNumber())) {
            isError = true;
            throw new CustomerParamException(DOCUMENT_NUMBER);
        }
        if (Util.isBlank(customerDTO.getEmail())) {
            isError = true;
            throw new CustomerParamException(EMAIL);
        }
        if (Util.isBlank(customerDTO.getPhone())) {
            isError = true;
            throw new CustomerParamException(PHONE);
        }
        if (isError) {
            createLog(VALIDATE_DATA, Result.ERROR, ERROR_DATA_EMPTY);
        }
    }

    private Optional<Customer> existsCustomer(long idCustomer) {
        Optional<Customer> customerResult = customerRepository.findById(idCustomer);
        if (!customerResult.isPresent()) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EMPTY_ID);
            description.append(" ");
            description.append(idCustomer);
            createLog(ACTION_EMPTY_DATA, Result.ERROR, description.toString());
            throw new CustomerNotFoundException();
        }
        return customerResult;
    }

    private void existsDocumentNumber(String documentNumber) {
        if (customerRepository.findCustomerBydocumentNumber(documentNumber) != null) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EXIST_CUSTOMER);
            description.append(" ");
            description.append(documentNumber);
            createLog(ACTION_SAVE, Result.ERROR, description.toString());
            throw new CustomerFoundException();
        }
    }

    private List<Purchase> existsRelationCustomer(long idCustomer) {
        List<Purchase> purchaseResult = purchaseRepository.findProductByCustomerIdCustomerOrProductIdProductOrSellerIdSeller(idCustomer, null, null);
        if (!purchaseResult.isEmpty()) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_ID_CUSTOMER);
            description.append(" ");
            description.append(idCustomer);
            createLog(ACTION_DELETE, Result.ERROR, description.toString());
            throw new SellerInvalidDeleteException();
        }
        return purchaseResult;
    }

    private void createLog(String action, Result result, String description) {
        LogTransation logTransation = LogTransation.builder()
                .module(action)
                .Result(result)
                .description(description).build();
        logTransationRepository.save(logTransation);
    }

}
