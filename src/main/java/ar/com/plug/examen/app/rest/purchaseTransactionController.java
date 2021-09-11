package ar.com.plug.examen.app.rest;


import ar.com.plug.examen.domain.model.purchaseTransactionModel;
import ar.com.plug.examen.domain.service.purchaseTransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/purchaseTransactions")
public class purchaseTransactionController {

    @Autowired
    purchaseTransactionService purchaseTransactionService;

    @GetMapping("/getAll")
    @ApiOperation("Get All purchase Transactions")
    private ResponseEntity<List<purchaseTransactionModel>> getAllPurchaseTransactions (){
        return ResponseEntity.ok(purchaseTransactionService.getAll());
    }

    @PostMapping("/save")
    @ApiOperation("save a purchase Transaction")
    public ResponseEntity<purchaseTransactionModel> savePurchaseTransaction(@RequestBody purchaseTransactionModel purchaseTransactionModel){

        try
        {
            purchaseTransactionModel transactionSaved = purchaseTransactionService.saveTransaction(purchaseTransactionModel);
            return ResponseEntity.created(new URI("/purchaseTransactions/"+transactionSaved.getId())).body(transactionSaved);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
