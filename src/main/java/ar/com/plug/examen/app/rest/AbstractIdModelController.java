package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.IdModel;
import ar.com.plug.examen.domain.service.ProcessIdModelService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractIdModelController<U extends ProcessIdModelService<T>, T extends IdModel> {

    //paths
    public static final String READ_PATH = "read";

    //error messages
    public static final String NAME_REQUIRED_MESSAGE = "name is required";
    public static final String ID_REQUIRED_MESSAGE = "id is required";
    public static final String ACTIVE_REQUIRED_MESSAGE = "active is required";

    final U processCustomerService;

    AbstractIdModelController(U processCustomerService) {
        this.processCustomerService = processCustomerService;
    }

    @PostMapping(path = READ_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> read(@RequestBody ObjectNode objectNode) {
        Long idRequired = getIdRequired(objectNode);
        return new ResponseEntity<>(processCustomerService.findById(idRequired), HttpStatus.OK);
    }

    static Long getId(@RequestBody ObjectNode objectNode) {
        JsonNode idNode = objectNode.get("id");
        if (null == idNode) {
            return null;
        } else {
            return idNode.asLong();
        }
    }

    static Long getIdRequired(@RequestBody ObjectNode objectNode) {
        Long id = getId(objectNode);
        if (null == id) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ID_REQUIRED_MESSAGE);
        }
        return id;
    }

    static String getName(@RequestBody ObjectNode objectNode) {
        JsonNode nameNode = objectNode.get("name");
        if (null == nameNode) {
            return null;
        } else {
            return nameNode.asText();
        }
    }

    static String getNameRequired(ObjectNode nameNode) {
        String name = getName(nameNode);
        if (null == name) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, NAME_REQUIRED_MESSAGE);
        }
        return name;
    }

    static Boolean getActive(@RequestBody ObjectNode objectNode) {
        JsonNode activeNode = objectNode.get("active");
        if (null == activeNode) {
            return null;
        } else {
            return activeNode.asBoolean();
        }
    }

    static Boolean getActiveRequired(@RequestBody ObjectNode objectNode) {
        Boolean aBoolean = getActive(objectNode);
        if (null == aBoolean) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, AbstractIdModelController.ACTIVE_REQUIRED_MESSAGE);
        }
        return aBoolean;
    }

}
