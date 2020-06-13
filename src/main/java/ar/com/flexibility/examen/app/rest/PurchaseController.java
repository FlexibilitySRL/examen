package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Purchase> find(@PathVariable Long customerId, @PathVariable Long productId) {
        return service.listPurchases(customerId, productId);
    }
    
    @PutMapping(path = "/{customerId}/{productId}")
    public Purchase create(@PathVariable Long customerId, @PathVariable Long productId) throws BadRequestException {
        try {
            return service.createPurchase(customerId, productId);
        }
        catch (Exception e) {
            throw new BadRequestException();
        }
    }
    
    @PostMapping(path = "/approve/{purchaseId}")
    public void approve(@PathVariable Long purchaseId) throws BadRequestException {
        if (!service.approve(purchaseId)) {
            throw new BadRequestException();
        }
    }
}
