package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.Customer;
import ar.com.plug.examen.datasource.repo.CustomerRepo;
import ar.com.plug.examen.domain.service.ProcessCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessCustomerServiceImpl implements ProcessCustomerService {

    private CustomerRepo customerRepo;

    @Autowired
    public ProcessCustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public void updateActive(Long id, Boolean active) {
        customerRepo.findById(id).ifPresent(customer -> customer.setActive(active));
    }

}
