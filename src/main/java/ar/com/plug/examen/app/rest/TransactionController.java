package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionApiRequest;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * RestController for transaction entity
 */
@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Get all transactions
     *
     * @return List<TransactionApi>
     */
    @GetMapping()
    public ResponseEntity<List<TransactionApi>> getTransactions() {
        return new ResponseEntity<>(this.transactionService.findAll(), HttpStatus.OK);
    }

    /**
     * Get transaction by id
     *
     * @param id
     * @return TransactionApi
     * @throws GenericNotFoundException
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity<TransactionApi> getTransactionById(@PathVariable("id") Long id) throws GenericNotFoundException {
        return new ResponseEntity<>(this.transactionService.findByIdChecked(id), HttpStatus.OK);
    }

    /**
     * Create a transaction
     *
     * @param transactionApi
     * @return TransactionApi
     * @throws GenericBadRequestException
     */
    @PostMapping()
    public ResponseEntity<TransactionApi> saveTransaction(@RequestBody TransactionApiRequest transactionApi) throws GenericBadRequestException {
        return new ResponseEntity<>(this.transactionService.save(transactionApi), HttpStatus.CREATED);
    }

    /**
     * Approve transaction by id
     *
     * @param id
     * @param status
     * @return TransactionApi
     * @throws GenericNotFoundException
     * @throws GenericBadRequestException
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<TransactionApi> approveTransaction(@PathVariable Long id, @RequestParam TransactionStatusEnum status) throws GenericNotFoundException, GenericBadRequestException {
        return new ResponseEntity<>(this.transactionService.updateStatus(id, status), HttpStatus.OK);
    }
}
