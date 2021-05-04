package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.BaseModel;
import ar.com.plug.examen.domain.service.ProcessBaseModelService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
public abstract class AbstractBaseModelController<U extends ProcessBaseModelService<T>, T extends BaseModel> {

    //paths
    public static final String CREATE_OR_UPDATE_PATH = "createOrUpdate";
    public static final String READ_PATH = "read";
    public static final String DELETE_PATH = "delete";

    final U service;

    AbstractBaseModelController(U service) {
        this.service = service;
    }

    @PostMapping(path = CREATE_OR_UPDATE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> createOrUpdate(@RequestBody ObjectNode objectNode) {
        try {
            return new ResponseEntity<>(service.createOrUpdate(objectNode), HttpStatus.OK);
        } catch (DataAccessException | IOException e) {
            final String message = String.format("Could not create object: %s with service %s", objectNode.toString(), service.getClass().getName());
            log.error(message, e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, message);
        }
    }

    @PostMapping(path = READ_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> read(@RequestBody Long id) {
        return new ResponseEntity<>(service.read(id), HttpStatus.OK);
    }

    @PostMapping(path = DELETE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> delete(@RequestBody Long id) {
        try {
            return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
        } catch (DataAccessException e) {
            final String message = String.format("Could not delete id: %s with service %s", id, service.getClass().getName());
            log.error(message, e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, message);
        }
    }

}
