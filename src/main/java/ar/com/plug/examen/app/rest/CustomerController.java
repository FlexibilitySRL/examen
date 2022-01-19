package ar.com.plug.examen.app.rest;

import java.util.ArrayList;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping()
    public ArrayList<Customer> obtenerCustomers(){
        return customerService.obtenerCustomers();
    }

    @PostMapping()
    public Customer crearCustomer(@RequestBody Customer customer){
        return this.customerService.crearCustomer(customer);
    }

    @PutMapping()
    public Customer modificarCustomer(@RequestBody Customer customer){
        return this.customerService.modificarCustomer(customer);
    }

    @DeleteMapping( path = "/{dni}")
    public String eliminarPorId(@PathVariable("dni") Long dni){
        boolean ok = this.customerService.eliminarCustomer(dni);
        if (ok){
            return "Se eliminó el cliente con dni " + dni;
        }else{
            return "No pudo eliminar el cliente con dni" + dni;
        }
    }

}