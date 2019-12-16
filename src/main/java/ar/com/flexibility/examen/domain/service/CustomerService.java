package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.core.service.AbstractService;
import ar.com.flexibility.examen.domain.model.Customer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * Servicio particular para la entidad Customer
 *
 */
@Service
public class CustomerService extends AbstractService<Customer> {
    private final static Logger logger = Logger.getLogger(CustomerService.class);

    @Override
    public Logger getLogger() {
        return logger;
    }
}
