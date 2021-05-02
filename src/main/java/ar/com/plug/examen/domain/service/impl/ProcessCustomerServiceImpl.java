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
    public Customer save(Long id, String name, Boolean active) {
        final Customer customer;
        final Customer newCustomer = Customer.builder().build();
        if (null == id) {
            customer = newCustomer;
        } else {
            customer = customerRepo.findById(id).orElse(newCustomer);
        }
        if (null != name) {
            customer.setName(name);
        }
        if (null != active) {
            customer.setActive(active);
        }
        return customerRepo.save(Customer.builder().id(id).name(name).active(active).build());
    }

    @Override
    public Customer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

}
