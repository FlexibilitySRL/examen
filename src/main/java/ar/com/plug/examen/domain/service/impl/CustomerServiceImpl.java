package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.converter.CustomerConverter;
import ar.com.plug.examen.domain.dto.Customer;
import ar.com.plug.examen.domain.model.CustomerModel;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import ar.com.plug.examen.objects.StatusTransaction;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private static final Log log = LogFactory.getLog(CustomerServiceImpl.class);

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final CustomerConverter customerConverter;

    @Override
    public JsonResponseTransaction addCustomer(CustomerModel customerModel) {
        log.info("Call: customer " + " addCustomerModel");
        Customer customer = customerRepository.save(customerConverter.convertFromEntity(customerModel));
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        jsonResponseTransaction = validateStatus(customerModel, jsonResponseTransaction);
        log.info(jsonResponseTransaction.getResponseMessage());
        jsonResponseTransaction.setCustomerModel(customerConverter.convertFromModel(customer));
        jsonResponseTransaction.setStatusTransaction(StatusTransaction.fromId(customerModel.getIdStatus()));
        return jsonResponseTransaction;
    }

    @Override
    public JsonResponseTransaction deleteCustomer(Long id) {
        log.info("Call: customer " + " deleteCustomer " + id);
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        customerRepository.deleteById(id);
        jsonResponseTransaction.setResponseMessage("Customer: "+id + " eliminated of system");
        log.info(jsonResponseTransaction.getResponseMessage());
        return jsonResponseTransaction;
    }

    @Override
    public JsonResponseTransaction updateCustomer(CustomerModel customerModel) {
        log.info("Call: customer " + " updateCustomerModel " + "customerModel before: " + customerConverter.convertFromModel(customerRepository.findById(customerModel.getId()))
                + System.lineSeparator() + " customerModel after: " + customerModel);
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        jsonResponseTransaction = validateStatus(customerModel, jsonResponseTransaction);
        log.info(jsonResponseTransaction.getResponseMessage());
        Customer customer = customerRepository.save(customerConverter.convertFromEntity(customerModel));
        jsonResponseTransaction.setCustomerModel(customerConverter.convertFromModel(customer));
        jsonResponseTransaction.setStatusTransaction(StatusTransaction.fromId(customerModel.getIdStatus()));
        return jsonResponseTransaction;
    }

    @Override
    public CustomerModel findCustomerModelById(Integer id) {
        return null;
    }

    private JsonResponseTransaction validateStatus(CustomerModel customerModel, JsonResponseTransaction jsonResponseTransaction) {
        if (Objects.equals(customerModel.getIdStatus(), StatusTransaction.INACTIVE.getId())) {
            jsonResponseTransaction.setResponseMessage("Customer add with successfully but status is inactive");
        }
        if (Objects.equals(customerModel.getIdStatus(), StatusTransaction.ACTIVE.getId())) {
            jsonResponseTransaction.setResponseMessage("Customer add with successfully");
        }
        return jsonResponseTransaction;
    }

}
