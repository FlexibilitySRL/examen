package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/purchase")
public class PurchaseController {
    
    @Autowired
    PurchaseService service;
    
    @GetMapping(path = "/{customerId}/{productId}")
    public ResponseEntity<List<Purchase>> find(@PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(service.listPurchases(customerId, productId), HttpStatus.OK);
    }
    
    @PutMapping(path = "/{customerId}/{productId}")
    public ResponseEntity<Purchase> create(@PathVariable Long customerId, @PathVariable Long productId) {
        return new ResponseEntity<>(service.createPurchase(customerId, productId), HttpStatus.CREATED);
    }
    
    @PostMapping(path = "/approve/{purchaseId}")
    public ResponseEntity<?> approve(@PathVariable Long purchaseId) {
        if (service.approve(purchaseId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
