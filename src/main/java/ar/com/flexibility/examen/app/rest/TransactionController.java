package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.rest.mapper.TransactionApiMapper;
import ar.com.flexibility.examen.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    private TransactionApiMapper mapper = new TransactionApiMapper();

    @GetMapping("/")
    public List<TransactionApi> list() {
        return transactionService
                .listAll()
                .stream()
                .map(entity -> mapper.buildApi(entity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{transactionId}")
    public List<TransactionApi> list(@Valid @PathVariable Long transactionId) throws ConstraintsViolationException {
        return transactionService
                .findByTransactionId(transactionId)
                .stream()
                .map(entity -> mapper.buildApi(entity))
                .collect(Collectors.toList());
    }
}
