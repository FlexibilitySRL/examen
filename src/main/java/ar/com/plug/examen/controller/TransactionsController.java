package ar.com.plug.examen.controller;

import ar.com.plug.examen.model.Transactions;
import ar.com.plug.examen.service.TransactionsService;
import ar.com.plug.examen.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contains methods for Transactions.
 *
 * @author Camilo Villate
 */
@RestController
@RequestMapping(
        path = "/api/v1/transactions"
)
public class TransactionsController {

    private final TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    /**
     * Create a new Transaction in the database
     *
     * @param transactions - Json model of the product
     * @return - response
     */
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> insertTransaction(@RequestBody Transactions transactions){
        int result = transactionsService.createTransaction(transactions);
        return MessageResponse.getIntegerResponseEntity(result);
    }

    /**
     * Retrieve list of Transactions from database
     *
     * @return - response Json Array whit all sellers
     */
    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Transactions> fetchTransactions(){
        return transactionsService.getAllSellers();
    }

    /**
     * Update Transaction in the database
     *
     * @param id - id to be update in database
     * @return - response a json message
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "{id}"
    )
    public ResponseEntity<?> updateStateTransaction(@PathVariable("id") Long id){
        int result = transactionsService.updateTransactionState(id,"APPROVED");
        return MessageResponse.getIntegerResponseEntity(result);
    }

}
