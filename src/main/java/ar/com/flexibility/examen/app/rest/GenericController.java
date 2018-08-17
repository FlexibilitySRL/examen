package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.GenericApi;
import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.app.rest.mapper.EntityMapper;
import ar.com.flexibility.examen.domain.model.GenericEntity;
import ar.com.flexibility.examen.domain.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public class GenericController<
        T extends GenericApi,
        E extends GenericEntity,
        M extends EntityMapper,
        S extends GenericService> {

    private S service;
    private M mapper;

    protected void setService(S service) {
        this.service = service;
    }

    protected void setMapper(M mapper) {
        this.mapper = mapper;
    }

    @GetMapping("{id}")
    public T find(@Valid @PathVariable long id) throws EntityNotFoundException {
        return (T) mapper
                .buildApi(service.findOne(id));
    }

    @GetMapping("/")
    public List<T> list() {
        return (List<T>) service
                .listAll()
                .stream()
                .map(entity -> mapper.buildApi((E)entity))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@Valid @RequestBody T api) throws ConstraintsViolationException {
        return (T) mapper
                .buildApi(
                        service
                                .create(
                                        mapper.buildEntity(api)));
    }

    @PutMapping
    public T update(@Valid @RequestBody T productApi) throws EntityNotFoundException {
        return (T) mapper
                .buildApi(
                        service
                                .save(
                                        mapper.buildEntity(productApi)));
    }

    @DeleteMapping("{id}")
    public T delete(@Valid @PathVariable Long id) throws ConstraintsViolationException, EntityNotFoundException {
        return (T) mapper
                .buildApi(
                        service
                                .delete(id));
    }
}
