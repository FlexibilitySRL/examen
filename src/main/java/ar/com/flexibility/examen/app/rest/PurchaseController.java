package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseStatus;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Purchase>> findAll() {
        return  ResponseEntity.ok(purchaseService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Purchase> find(@PathVariable Integer id) {
        return  ResponseEntity.ok(purchaseService.find(id));
    }

    @PostMapping("/new/{sellerId}/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Integer> createPurchase(@PathVariable Integer sellerId, @PathVariable Integer customerId) {
        return ResponseEntity.ok(purchaseService.createNewPurchase(sellerId, customerId).getId());
    }

    @PutMapping("/addItem/{purchaseId}/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void endPurchase(@PathVariable Integer purchaseId, @PathVariable Integer productId) {
        purchaseService.addProductToPurchase(purchaseId, productId);
    }

    @PutMapping("/end/{purchaseId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void endPurchase(@PathVariable Integer purchaseId) {
        purchaseService.endPurchase(purchaseId);
    }


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void approve(@PathVariable Integer id) {
        purchaseService.changeStatusToPurchase(id, PurchaseStatus.APPROVED);
    }
}
