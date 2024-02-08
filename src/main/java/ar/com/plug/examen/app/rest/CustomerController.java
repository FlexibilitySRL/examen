package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.DTO.CustomerDTO;
import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.app.mapper.CustomerMapper;
import ar.com.plug.examen.domain.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerMapper mapper;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    /**
     * Agrega un nuevo clientes.
     */
    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerApi customerApi) {
        CustomerDTO customerAdded = customerService.addCustomer(mapper.asDTO(customerApi));
        log.info("Clientes agregado con éxito: {}", customerApi.getName());
        return ResponseEntity.ok(customerAdded);
    }

    /**
     * Actualiza la información de un clientes.
     */
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerApi customerApi) {
        CustomerDTO customerUpdated = customerService.updateCustomer(customerId, mapper.asDTO(customerApi));
        log.info("Clientes actualizado con éxito: {}", customerApi.getName());
        return ResponseEntity.ok(customerUpdated);
    }

    /**
     * Elimina un clientes.
     */
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
        log.info("Clientes eliminado con éxito: {}", customerId);
        return ResponseEntity.ok().build();
    }

    /**
     * Obtiene la lista de todos los clientes.
     */
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        log.info("Consulta exitosa de todos los clientes.");
        return ResponseEntity.ok(customers);
    }

    /**
     * Obtiene la lista de todos los clientes.
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable UUID customerId) {
        Optional<CustomerDTO> customer = customerService.getCustomerById(customerId);
        log.info("Consulta exitosa de todos los clientes.");
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
