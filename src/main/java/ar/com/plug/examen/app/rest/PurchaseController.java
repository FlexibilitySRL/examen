package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.PurchaseApi;
import ar.com.plug.examen.domain.service.PurchaseUseCase;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseUseCase service;

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody final PurchaseApi purchaseApi) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.save(purchaseApi), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactaction(@PathVariable final Long id) {
        return new ResponseEntity<>(service.getTransaction(id), HttpStatus.OK);
    }



}
