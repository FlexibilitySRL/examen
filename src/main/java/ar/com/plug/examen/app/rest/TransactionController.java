package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionItem;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.utils.MessageFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    // -------------------Retrieve All Transactions--------------------------------------------
    @GetMapping
    public ResponseEntity<List<Transaction>> listAllTransactions() {
        List<Transaction> transactions = transactionService.findTransactionAll();
        if (transactions.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(transactions);
    }

    // -------------------Retrieve Single Transaction------------------------------------------
    @GetMapping(value = "/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") long id) {
        log.info("Fetching transaction with id {}", id);
        Optional<Transaction> transaction  = Optional.ofNullable(transactionService.getTransaction(id));
        if (transaction.isEmpty()) {
            log.error("Transaction with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(transaction.get());
    }

    // -------------------Create a Transaction-------------------------------------------
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction, BindingResult result) {
        log.info("Creating transaction : {}", transaction);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MessageFormat.formatMessage(result));
        }
        Transaction transactionDB = transactionService.createTransaction(transaction);

        return  ResponseEntity.status( HttpStatus.CREATED).body(transactionDB);
    }


    // -------------------Add items to Transaction-------------------------------------------
    @PostMapping(value = "/addItems/{id}")
    public ResponseEntity<Transaction> addItemsToTransaction(@PathVariable("id") long id, @Valid @RequestBody List<TransactionItem> transactions, BindingResult result) {
        log.info("Adding transactions : {}", transactions);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MessageFormat.formatMessage(result));
        }
        Transaction transactionDB = transactionService.addItemsToTransaction(id, transactions);

        return  ResponseEntity.status( HttpStatus.CREATED).body(transactionDB);
    }


    // -------------------Change Approve Transaction------------------------------------------
    @GetMapping(value = "/approve/{id}")
    public ResponseEntity<Transaction> changeApproveTransaction(@PathVariable("id") long id, @RequestParam(name = "approve") Boolean approve) {
        log.info("Fetching transaction with id {}", id);
        Optional<Transaction> transaction  = Optional.ofNullable(transactionService.getTransaction(id));
        if (transaction.isEmpty()) {
            log.error("Transaction with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        Transaction transactionDB = transactionService.changeApproveTransaction(id, approve);
        return  ResponseEntity.ok(transactionDB);
    }


    // ------------------- Update a Transaction ------------------------------------------------
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction) {
        log.info("Updating transaction with id {}", id);

        transaction.setId(id);
        Optional<Transaction> currentTransaction = Optional.ofNullable(transactionService.updateTransaction(transaction));

        if (currentTransaction.isEmpty()) {
            log.error("Unable to update. Transaction with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentTransaction.get());
    }

    // ------------------- Delete a Transaction-----------------------------------------
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Transaction with id {}", id);

        Optional<Transaction> transaction = Optional.ofNullable(transactionService.getTransaction(id));
        if (transaction.isEmpty()) {
            log.error("Unable to delete. Transaction with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        Transaction transactionDeleted = transactionService.deleteTransaction(transaction.get());
        return ResponseEntity.ok(transactionDeleted);
    }
}