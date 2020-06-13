package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/purchase")
public class PurchaseController {
    
    @Autowired
    PurchaseService service;
    
    @RequestMapping(path = "/{customerId}/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<Purchase>> find(@PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(service.listPurchases(customerId, productId), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/{customerId}/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<Purchase> create(@PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(service.createPurchase(customerId, productId), HttpStatus.CREATED);
    }
    
    @RequestMapping(path = "/approve/{purchaseId}", method = RequestMethod.POST)
    public ResponseEntity<?> approve(@PathVariable Long purchaseId) {
        if (service.approve(purchaseId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
