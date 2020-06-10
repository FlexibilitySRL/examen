package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sellers/{id}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private TransactionService transactionService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionApi> createTransaction(@RequestBody TransactionApi transactionApi) {
        return new ResponseEntity<>(transactionService.create(transactionApi), HttpStatus.CREATED);

    }

    /**
     * List all the transactions of a seller
     *
     * @param id seller id
     * @return List of transactions
     */
    @GetMapping
    public ResponseEntity<List<TransactionApi>> listByFilters(@RequestParam("id") Long id) {
        return new ResponseEntity<>(transactionService.list(id), HttpStatus.OK);
    }

    /**
     * Search a transaction by id and performs an action on it
     *
     * @param id     transaction is
     * @param action action to perform
     * @return modified Transaction
     */
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionApi> updateTransaction(@PathVariable Long id,
                                                            @RequestParam(defaultValue = "approve") String action) {
        return new ResponseEntity<>(transactionService.processAction(id, action), HttpStatus.OK);
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
