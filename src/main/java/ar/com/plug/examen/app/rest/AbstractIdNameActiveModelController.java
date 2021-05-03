package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.IdNameActiveModel;
import ar.com.plug.examen.domain.service.ProcessIdNameActiveModelService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractIdNameActiveModelController<U extends ProcessIdNameActiveModelService<T>, T extends IdNameActiveModel>
        extends AbstractIdModelController<U, T> {

    //paths
    public static final String CREATE_PATH = "create";
    public static final String UPDATE_PATH = "update";
    public static final String DELETE_PATH = "delete";

    AbstractIdNameActiveModelController(U service) {
        super(service);
    }

    @PostMapping(path = CREATE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody ObjectNode objectNode) {
        String name = getNameRequired(objectNode, "name");
        Boolean active = getActiveRequired(objectNode, "active");
        return new ResponseEntity<>(service.save(null, name, active), HttpStatus.OK);
    }

    @PostMapping(path = UPDATE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> update(@RequestBody ObjectNode objectNode) {
        Long id = getIdRequired(objectNode);
        String name = getString(objectNode, "name");
        Boolean active = getBoolean(objectNode, "active");
        return new ResponseEntity<>(service.save(id, name, active), HttpStatus.OK);
    }

    @PostMapping(path = DELETE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> delete(@RequestBody ObjectNode objectNode) {
        Long id = getIdRequired(objectNode);
        return new ResponseEntity<>(service.save(id, null, false), HttpStatus.OK);
    }

}
