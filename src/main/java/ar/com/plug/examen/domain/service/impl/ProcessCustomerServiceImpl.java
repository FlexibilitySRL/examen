package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessCustomerServiceImpl extends AbstractIdNameActiveModelService<Customer> {

    public ProcessCustomerServiceImpl(JpaRepository<Customer, Long> repo) {
        super(repo);
    }

    @Override
    Class<Customer> getDomainClass() {
        return Customer.class;
    }
}
