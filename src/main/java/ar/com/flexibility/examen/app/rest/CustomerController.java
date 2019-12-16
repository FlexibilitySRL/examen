package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.core.rest.AbstractController;
import ar.com.flexibility.examen.domain.model.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends AbstractController<Customer> {

}
