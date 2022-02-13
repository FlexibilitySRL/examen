package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.exception.ClientNotFoundException;
import ar.com.plug.examen.domain.exception.ErrorResponse;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.exception.TransactionNotFoundException;
import ar.com.plug.examen.domain.exception.VendorNotFoundException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionStatus;
import ar.com.plug.examen.domain.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
@Tag(name = "Transaction service", description = "getById, create and confirm transaction")
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionId") long transactionId) {
        return new ResponseEntity<>(transactionService.findById(transactionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionApi transactionApi) {
        return new ResponseEntity<>(transactionService.save(transactionApi), HttpStatus.CREATED);
    }

    @PutMapping("/{transactionId}/confirm")
    public ResponseEntity<Transaction> approveTransaction(@PathVariable("transactionId") long transactionId) {
        Transaction transaction = transactionService.findById(transactionId);
        transaction.setStatus(TransactionStatus.APPROVED);
        return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.OK);
    }

    @ExceptionHandler({ClientNotFoundException.class, ProductNotFoundException.class, TransactionNotFoundException.class, VendorNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
