package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.rest.responses.ChallengeResponse;
import ar.com.plug.examen.domain.execptions.BadRequestException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    private static final String STATUS_SUCCESS = "Success";

    @Autowired
    private TransactionService transactionService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public ChallengeResponse<TransactionApi> createTransaction(@RequestBody TransactionApi TransactionApi) {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.CREATED), "OK", transactionService.createTransaction(TransactionApi));
    }

    @GetMapping()
    public ChallengeResponse<List<TransactionApi>> listAllTransactions() {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.CREATED), "OK", transactionService.listAllTransactions());
    }

    @GetMapping(path = "/{id}")
    public ChallengeResponse<List<TransactionApi>> getTransactionBySellerId(@PathVariable Long id) {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.CREATED), "OK", transactionService.getTransactionBySellerId(id));

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChallengeResponse<TransactionApi> updateTransaction(@PathVariable Long id, @RequestParam String validation) throws BadRequestException, NotFoundException {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.CREATED), "OK", transactionService.approveTransaction(id, validation));
    }

}
