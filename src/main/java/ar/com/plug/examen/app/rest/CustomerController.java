package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Customer;
import ar.com.plug.examen.domain.service.impl.ProcessCustomerModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = CustomerController.ROOT_PATH)
public class CustomerController
        extends AbstractBaseModelController<ProcessCustomerModelServiceImpl, Customer> {

    public static final String ROOT_PATH = "customer";

    @Autowired
    public CustomerController(ProcessCustomerModelServiceImpl processCustomerService) {
        super(processCustomerService);
    }

}
