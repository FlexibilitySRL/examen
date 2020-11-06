package ar.com.plug.examen.controller;

import ar.com.plug.examen.model.Transactions;
import ar.com.plug.examen.service.TransactionsService;
import ar.com.plug.examen.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> insertTransaction(@RequestBody Transactions transactions){
        int result = transactionsService.createTransaction(transactions);
        return getIntegerResponseEntity(result);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Transactions> fetchTransactions(){
        return transactionsService.getAllSellers();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{id}"
    )
    public ResponseEntity<?> updateStateTransaction(@PathVariable("id") Long id){
        int result = transactionsService.updateTransactionState(id,"APPROVED");
        return getIntegerResponseEntity(result);
    }

    private ResponseEntity<?> getIntegerResponseEntity(int result) {
        if(result ==1){
            return  ResponseEntity.ok().body(new MessageResponse("Proceso correcto"));
        }
        return ResponseEntity.badRequest().build();
    }
}
