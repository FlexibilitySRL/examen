package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Customer;
import ar.com.plug.examen.domain.service.ProcessCustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = CustomerController.ROOT_PATH)
public class CustomerController {

    //paths
    public static final String ROOT_PATH = "customer";
    public static final String CREATE_PATH = "create";
    public static final String READ_PATH = "read";
    public static final String UPDATE_PATH = "update";
    public static final String DELETE_PATH = "delete";

    //error messages
    public static final String NAME_REQUIRED_MESSAGE = "name is required";
    public static final String ID_REQUIRED_MESSAGE = "id is required";
    public static final String ACTIVE_REQUIRED_MESSAGE = "active is required";

    private final ProcessCustomerService processCustomerService;

    @Autowired
    public CustomerController(ProcessCustomerService processCustomerService) {
        this.processCustomerService = processCustomerService;
    }

    @PostMapping(path = CREATE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> create(@RequestBody ObjectNode objectNode) {
        String name = getNameRequired(objectNode);
        Boolean active = getActiveRequired(objectNode);
        return new ResponseEntity<>(processCustomerService.save(null, name, active), HttpStatus.OK);
    }

    @PostMapping(path = READ_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> read(@RequestBody ObjectNode objectNode) {
        Long idRequired = getIdRequired(objectNode);
        return new ResponseEntity<>(processCustomerService.findById(idRequired), HttpStatus.OK);
    }

    @PostMapping(path = UPDATE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> update(@RequestBody ObjectNode objectNode) {
        Long id = getIdRequired(objectNode);
        String name = getName(objectNode);
        Boolean active = getActive(objectNode);
        return new ResponseEntity<>(processCustomerService.save(id, name, active), HttpStatus.OK);
    }

    @PostMapping(path = DELETE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> delete(@RequestBody ObjectNode objectNode) {
        Long id = getIdRequired(objectNode);
        return new ResponseEntity<>(processCustomerService.save(id, null, false), HttpStatus.OK);
    }

    private Long getId(@RequestBody ObjectNode objectNode) {
        JsonNode idNode = objectNode.get("id");
        if (null == idNode) {
            return null;
        } else {
            return idNode.asLong();
        }
    }

    private Long getIdRequired(@RequestBody ObjectNode objectNode) {
        Long id = getId(objectNode);
        if (null == id) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ID_REQUIRED_MESSAGE);
        }
        return id;
    }

    private String getName(@RequestBody ObjectNode objectNode) {
        JsonNode nameNode = objectNode.get("name");
        if (null == nameNode) {
            return null;
        } else {
            return nameNode.asText();
        }
    }

    private String getNameRequired(ObjectNode nameNode) {
        String name = getName(nameNode);
        if (null == name) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, NAME_REQUIRED_MESSAGE);
        }
        return name;
    }

    private static Boolean getActive(@RequestBody ObjectNode objectNode) {
        JsonNode activeNode = objectNode.get("active");
        if (null == activeNode) {
            return null;
        } else {
            return activeNode.asBoolean();
        }
    }

    private static Boolean getActiveRequired(@RequestBody ObjectNode objectNode) {
        Boolean aBoolean = getActive(objectNode);
        if (null == aBoolean) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, CustomerController.ACTIVE_REQUIRED_MESSAGE);
        }
        return aBoolean;
    }

}
