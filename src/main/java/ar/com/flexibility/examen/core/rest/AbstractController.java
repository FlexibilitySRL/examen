package ar.com.flexibility.examen.core.rest;

import ar.com.flexibility.examen.core.model.AbstractEntity;
import ar.com.flexibility.examen.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 *
 * Controller abstracto que por medio del servicio de cada entidad expone el CRUD como API REST.
 *
 * @param <T>
 */
public abstract class AbstractController<T extends AbstractEntity> {
    private AbstractService<T> service;

    public AbstractService<T> getService() {
        return this.service;
    }

    @Autowired
    public void setService(AbstractService<T> service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(this.getService().findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<T> find(@PathVariable Integer id) {
        return ResponseEntity.ok(this.getService().find(id));
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> save(@RequestBody T entity) {
        T savedEntity = this.getService().save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{i}").buildAndExpand(savedEntity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void remove(@PathVariable Integer id) {
        this.getService().remove(id);
    }
}
