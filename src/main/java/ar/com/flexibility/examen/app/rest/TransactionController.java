package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityConflictException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.app.rest.mapper.TransactionApiMapper;
import ar.com.flexibility.examen.domain.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for {@link ar.com.flexibility.examen.domain.model.Transaction} operations.
 */
@RestController
@RequestMapping(path = "/transaction")
@Api(description = "Controller for Transaction operations.")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    private TransactionApiMapper mapper = new TransactionApiMapper();

    @ApiOperation("Returns a transaction list.")
    @GetMapping("/")
    public List<TransactionApi> list() {
        return transactionService
                .listAll()
                .stream()
                .map(entity -> mapper.buildApi(entity))
                .collect(Collectors.toList());
    }

    @ApiOperation("Returns a transaction list by transactionId.")
    @GetMapping("/{transactionId}")
    public List<TransactionApi> list(@Valid @PathVariable Long transactionId) throws ConstraintsViolationException {
        return transactionService
                .findByTransactionId(transactionId)
                .stream()
                .map(entity -> mapper.buildApi(entity))
                .collect(Collectors.toList());
    }

    @ApiOperation("Set a transaction with transactionId as approved.")
    @GetMapping("/{transactionId}/approve")
    public List<TransactionApi> approve(@Valid @PathVariable Long transactionId) throws ConstraintsViolationException, EntityConflictException, EntityNotFoundException {
        return transactionService
                .approveTransaction(transactionId)
                .stream()
                .map(entity -> mapper.buildApi(entity))
                .collect(Collectors.toList());
    }
}
