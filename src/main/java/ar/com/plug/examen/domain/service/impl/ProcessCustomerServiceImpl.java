package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.Customer;
import ar.com.plug.examen.datasource.repo.CustomerRepo;
import org.springframework.stereotype.Service;

@Service
public class ProcessCustomerServiceImpl extends AbstractIdNameActiveModelService<CustomerRepo, Customer> {

    public ProcessCustomerServiceImpl(CustomerRepo repo) {
        super(repo);
    }

    @Override
    Class<Customer> getDomainClass() {
        return Customer.class;
    }
}
