package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.BaseModel;
import ar.com.plug.examen.domain.service.ProcessBaseModelService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractBaseModelController<U extends ProcessBaseModelService<T>, T extends BaseModel> {

    //paths
    public static final String CREATE_PATH = "create";
    public static final String READ_PATH = "read";
    public static final String UPDATE_PATH = "update";
    public static final String DELETE_PATH = "delete";

    //error messages
    public static final String NAME_REQUIRED_MESSAGE = "name is required";
    public static final String ID_REQUIRED_MESSAGE = "id is required";
    public static final String ACTIVE_REQUIRED_MESSAGE = "active is required";

    final U service;

    AbstractBaseModelController(U service) {
        this.service = service;
    }

    @PostMapping(path = CREATE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody ObjectNode objectNode) {
        return new ResponseEntity<>(service.createOrUpdate(objectNode), HttpStatus.OK);
    }

    @PostMapping(path = READ_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> read(@RequestBody Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @PostMapping(path = UPDATE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@RequestBody ObjectNode objectNode) {
        return new ResponseEntity<>(service.createOrUpdate(objectNode), HttpStatus.OK);
    }

    @PostMapping(path = DELETE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> delete(@RequestBody Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
