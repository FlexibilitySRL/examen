package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.service.ProductUseCase;
import ar.com.plug.examen.infrastructure.exception.ProductDuplicateException;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductUseCase service;

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody final ProductApi productApi)
            throws ProductDuplicateException {
        return new ResponseEntity<>(service.save(productApi), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody final ProductApi productApi, @PathVariable final Long id)
            throws ResourceNotFoundException, ProductDuplicateException {
        return new ResponseEntity<>(service.update(productApi,id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable final Long id)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(service.delete(id), HttpStatus.ACCEPTED);
    }

}
