package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionApi> createTransaction(@RequestBody TransactionApi transactionApi) {
        return new ResponseEntity<>(transactionService.create(transactionApi), HttpStatus.CREATED);
    }

    @GetMapping("/getTransaction/{id}")
    public ResponseEntity<TransactionApi> getTransaction(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.get(id), HttpStatus.OK);
    }
    
    @GetMapping("/getTransactions")
    public ResponseEntity<List<TransactionApi>> getTransactions() {
        return new ResponseEntity<>(transactionService.getTransactions(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
    	transactionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/updateTransaction/{id}")
    public ResponseEntity<TransactionApi> updateTransaction(@PathVariable Long id, @RequestBody TransactionApi transactionApi) {
        return new ResponseEntity<>(transactionService.update(id, transactionApi), HttpStatus.OK);
    }
    
    public ResponseEntity<TransactionApi> approveTransaction(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.approveTransaction(id), HttpStatus.OK);
    }

}