package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.service.TransactionService;

@RestController
@RequestMapping(path = Paths.TRANSACTION)
public class TransactionController {

	@Autowired
    private TransactionService transactionService;

    @GetMapping()
    public List<TransactionApi> listTransactions() {
        return transactionService.listAll();
    }

    @GetMapping(Paths.FIND_BY_FILTERS)
    public List<TransactionApi> findBySellerId(@RequestBody TransactionApi filters) {
        return transactionService.findByFilters(filters);
    }
    
    @PostMapping()
    public TransactionApi save(@RequestBody TransactionApi transaction) throws BadRequestException, NotFoundException {
    	return transactionService.save(transaction);
    }

	@DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        transactionService.deleteById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionApi updateTransactionStatusById(@RequestParam(name = "id") Long id, 
    		@RequestParam(name = "status") String status) throws NotFoundException, BadRequestException {
    	return transactionService.updateTransactionStatusById(id, status);
    }
}
