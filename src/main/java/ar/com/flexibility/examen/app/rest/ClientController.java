package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<?> listClients() {
        log.trace("Request to GET all the clients");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.all());
    }

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<?> getClient(@PathVariable Long clientId) {
        log.trace("Request to GET clientId: {}", clientId);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.get(clientId));
    }

    @GetMapping(value = "/{clientId}/transactions")
    public ResponseEntity<List<TransactionApi>> allTransactionsForClientId(@PathVariable Long clientId) {
        log.trace("Request to GET all transaction for vendor clientId: {}", clientId);
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.allByClient(clientId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientApi clientApi) {
        log.trace("Request to POST new client: {}", clientApi);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(clientApi));
    }

    @PutMapping(value = "/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeClient(@PathVariable Long clientId, @Valid @RequestBody ClientApi clientApi) {
        log.trace("Request to PUT with clientId {} with name: {} ", clientId, clientApi.getName());
        return ResponseEntity.status(HttpStatus.OK).body(clientService.update(clientId, clientApi));
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<?> removeClient(@PathVariable Long clientId) {
        log.trace("Request to DELETE with clientId {}", clientId);
        clientService.remove(clientId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
