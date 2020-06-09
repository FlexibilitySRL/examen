package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.dto.ApproveDTO;
import ar.com.flexibility.examen.domain.dto.TransactionPurchasesDTO;
import ar.com.flexibility.examen.domain.service.ApproveService;
import ar.com.flexibility.examen.domain.service.TransactionPurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class PurchaseController {

    private TransactionPurchasesService transactionService;

    @Autowired
    private ApproveService approveService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody @Valid  TransactionPurchasesDTO transactionDto)
    {
        return new ResponseEntity<TransactionPurchasesDTO>(transactionService.create(transactionDto) , HttpStatus.OK);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<?> purchase(@PathVariable(name="transactionId") String  transactionId)
    {
        return new ResponseEntity<TransactionPurchasesDTO>(transactionService.findByTransaction(transactionId) , HttpStatus.OK);
    }

    @PostMapping("/approve/{transactionId}")
    public ResponseEntity<?> approve(@PathVariable(name="transactionId") String  transactionId)
    {
        return new ResponseEntity<ApproveDTO>(approveService.approve(transactionId) , HttpStatus.OK);
    }


    @Autowired
    public void setTransactionService(TransactionPurchasesService transactionService) {
        this.transactionService = transactionService;
    }

}
