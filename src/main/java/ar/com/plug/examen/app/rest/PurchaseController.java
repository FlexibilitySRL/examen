package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.aspect.LogginAspect;
import ar.com.plug.examen.domain.exceptions.PurchaseDoesNotExistException;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Purchase API")
public class PurchaseController {

    @Autowired
    private PurchaseServiceImpl service;

    @GetMapping(value = "/purchases")
    @LogginAspect
    @ApiOperation(value = "Get the purchases list", notes = "Return the list of purchases" )
    public ResponseEntity<?> getPurchases(){
        List<Purchase> purchases = this.service.findAll();
        return ResponseEntity.ok().body(purchases);
    }

    @GetMapping(value = "/purchase/{id}")
    @LogginAspect
    @ApiOperation(value = "Find a purchase", notes = "Return a purchase by Id")
    public ResponseEntity<?> getPurchase(@PathVariable("id") Long id){
        Purchase purchase = null;
        try {
            purchase = this.service.findById(id);
        } catch (PurchaseDoesNotExistException e) {
            return new ResponseEntity<>(purchase, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(purchase);
    }

    @PostMapping(path="/purchase", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @LogginAspect
    @ApiOperation(value = "Add a purchase", notes = "Return the purhchase saved passed by param" )
    public ResponseEntity<?> createPurchase(@RequestBody Purchase aPurchase){
        Purchase purchase = this.service.savePurchase(aPurchase);
        return ResponseEntity.ok().body(purchase);
    }

    @PutMapping(path = "/purchase/{id}", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @LogginAspect
    @ApiOperation(value = "Update a purchase", notes = "Return the purchase updated referenced by id" )
    public ResponseEntity<?> updatePurchase(@RequestBody Purchase aPurchase){
        Purchase purchase = null;
        try {
            purchase = this.service.updatePurchase(aPurchase);
        } catch (PurchaseDoesNotExistException e) {
            return new ResponseEntity<>("The purchase does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(purchase);
    }

    @DeleteMapping(path="/purchase/delete/{id}")
    @LogginAspect
    @ApiOperation(value = "Delete a purchase", notes = "Delete the purchase referenced by id" )
    public ResponseEntity<?> deletePurchase(@PathVariable("id") Long id){
        this.service.deletePurchase(id);
        return ResponseEntity.ok().build();
    }
}
