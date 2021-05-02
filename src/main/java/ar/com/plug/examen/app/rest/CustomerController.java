package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Customer;
import ar.com.plug.examen.domain.service.ProcessCustomerService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = CustomerController.ROOT_PATH)
public class CustomerController {

    public static final String ROOT_PATH = "customer";
    public static final String UPDATE_ACTIVE_PATH = "updateActive";
    public static final String SAVE_PATH = "save";

    private final ProcessCustomerService processCustomerService;

    @Autowired
    public CustomerController(ProcessCustomerService processCustomerService) {
        this.processCustomerService = processCustomerService;
    }

    @PostMapping(path = SAVE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return new ResponseEntity<>(processCustomerService.save(customer), HttpStatus.OK);
    }

    @PostMapping(path = UPDATE_ACTIVE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectUtils.Null> echo(@RequestBody ObjectNode objectNode) {
        processCustomerService.updateActive(objectNode.get("id").asLong(), objectNode.get("active").asBoolean());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
