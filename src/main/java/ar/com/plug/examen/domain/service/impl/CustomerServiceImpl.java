package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.domain.common.EmailServiceValidator;
import ar.com.plug.examen.domain.common.impl.RequestTool;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.service.CustomerUseCase;
import ar.com.plug.examen.infrastructure.exception.CustomerEmailExistException;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerUseCase {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private EmailServiceValidator emailValidator;

    /**
     * This method allows save customer
     * @param  @{@link CustomerApi} customer
     * @return @{@link CustomerApi}
     * @throws CustomerEmailExistException
     */
    @Override
    public CustomerApi save(final CustomerApi customer) throws CustomerEmailExistException {
        log.info("save Customer begins");
        if (emailValidator.validateEmail().test(customer.getEmail())) {
            log.info("Duplicate Email");
            throw new CustomerEmailExistException("Duplicate Email");
        }
        log.info("Save Customer");
        return RequestTool.parseCustomer().apply(repository.save(RequestTool.swapCustomer(customer)));
    }

    /**
     * This method allows update customer
     * @param @{@link CustomerApi} customer
     * @param id
     * @return @{@link CustomerApi}
     * @throws CustomerEmailExistException
     * @throws ResourceNotFoundException
     */
    @Override
    public CustomerApi update(final CustomerApi customer,final Long id) throws CustomerEmailExistException, ResourceNotFoundException {
        log.info("Update Customer begins");
        Customer customerEntity = findCustomer(id);
        if (emailValidator.validateEmailUpdate().apply(customerEntity.getEmail(), customer.getEmail())) {
            log.info("Duplicate Email");
            throw new CustomerEmailExistException("Duplicate Email");
        }
        return RequestTool.parseCustomer().apply(repository.save(RequestTool.swapCustomer(customer,id)));
    }

    /**
     * This method allow delete customer
     * @param id
     * @return Boolean
     * @throws ResourceNotFoundException
     */
    @Override
    public Boolean delete(Long id) throws ResourceNotFoundException {
        log.info("Delete Customer begins");
        Customer customerEntity = findCustomer(id);
        return repository.deleteCustomer(customerEntity.getId()) == 1 ? true : false;

    }



    private Customer findCustomer(Long id) throws ResourceNotFoundException {
        log.info("find Customer");
        return repository.findCustomerById(id).orElseThrow(
                () -> {
                    log.info("User not found with userId " + id);
                    return new ResourceNotFoundException("User not found with userId " + id);
                }
        );
    }
}
