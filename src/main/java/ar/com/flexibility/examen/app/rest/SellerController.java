package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.service.SellerService;
import ar.com.flexibility.examen.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sellers", produces = MediaType.APPLICATION_JSON_VALUE)
public class SellerController {

    private SellerService sellerService;
    private TransactionService transactionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SellerApi> createSeller(@RequestBody SellerApi sellerApi) {
        return new ResponseEntity<>(sellerService.create(sellerApi), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerApi> retrieveSeller(@PathVariable Long id) {
        return new ResponseEntity<>(sellerService.retrieve(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SellerApi>> listSellers() {
        return new ResponseEntity<>(sellerService.list(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeSellers(@PathVariable Long id) {
        sellerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SellerApi> updateSellers(@PathVariable Long id, @RequestBody SellerApi sellerApi) {
        return new ResponseEntity<>(sellerService.update(id, sellerApi), HttpStatus.OK);
    }

    @Autowired
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }
}
