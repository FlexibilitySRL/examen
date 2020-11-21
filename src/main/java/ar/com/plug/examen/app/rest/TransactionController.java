package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.rest.paths.Paths;
import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.service.TransactionService;

@RestController
@RequestMapping(path = Paths.TRANSACTION)
public class TransactionController {

	@Autowired
    private TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<List<TransactionApi>> listTransactions() {
        return new ResponseEntity<>(transactionService.listAll(), HttpStatus.OK);
    }

    @GetMapping(Paths.FIND_BY_FILTERS)
    public  ResponseEntity<List<TransactionApi>> findBySellerId(@RequestBody TransactionApi filters) {
        return new ResponseEntity<>(transactionService.findByFilters(filters), HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<TransactionApi> save(@RequestBody TransactionApi transaction) throws BadRequestException, NotFoundException {
    	return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.CREATED);
    }

	@DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws NotFoundException, BadRequestException {
        transactionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionApi> updateTransactionStatusById(@RequestParam(name = "id") Long id, 
    		@RequestParam(name = "status") StatusEnum status) throws NotFoundException, BadRequestException {
    	return new ResponseEntity<>(transactionService.updateTransactionStatusById(id, status), HttpStatus.ACCEPTED);
    }

    @GetMapping(Paths.TOTAL_AMOUNT_BY_TRANSACTION_ID)
    public ResponseEntity<Double> totalAmountByTransactionId(@PathVariable Long id) throws NotFoundException, BadRequestException {
    	return new ResponseEntity<>(transactionService.totalAmountByTransactionId(id), HttpStatus.OK);
    }
}
