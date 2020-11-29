/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.TransactionApiRequest;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.TransactionService;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author msulbara
 */
@RestController
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;
    private final ClientService clientService;
    private final ProductService productService;

    public TransactionController(TransactionService transactionService, ClientService clientService, ProductService productService) {
        this.transactionService = transactionService;
        this.clientService = clientService;
        this.productService = productService;
    }

    @GetMapping(value = "/transactions")
    public ResponseEntity<Set<Transaction>> getTransactions() {
        Set<Transaction> transactions = transactionService.findAll();

        LOGGER.info("complete transactions size is: {}", transactions.size());

        return ResponseEntity.ok().body(transactions);
    }

    @GetMapping(value = "/transaction/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") Long id) {

        Transaction transaction = new Transaction();
        transaction.setId(id);
        try {
            transaction = transactionService.findById(id);
            return ResponseEntity.ok().body(transaction);
        } catch (RuntimeException ex) {
            LOGGER.info("no transaction for id: {}", id);
            return new ResponseEntity<>(transaction, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(
            path = "/transaction/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createTransaction(@RequestBody TransactionApiRequest request) {
        if (!isTransactionValid(request)) {
            return new ResponseEntity<>("client or some of the product do not exits!", HttpStatus.NOT_FOUND);
        } else {
            Transaction savedTransaction = transactionService.save(getTransactionEntityFromRequest(request));

            return ResponseEntity.ok().body(savedTransaction);
        }
    }

    @PutMapping(
            path = "/transaction/approval/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> approveTransaction(@PathVariable("id") Long id) {
        try {
            Transaction trandaction = transactionService.findById(id);

            if (transactionService.approveTransaction(trandaction)) {
                return ResponseEntity.ok().body(transactionService.findById(id));
            } else {
                return new ResponseEntity<>("trasaction cloud not be approved", HttpStatus.CONFLICT);
            }
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("trasaction does not exists!", HttpStatus.NOT_FOUND);
        }
    }

    private boolean isTransactionValid(TransactionApiRequest request) {
        boolean isValid = false;
        try {
            LOGGER.info("client id: {}", request.getClientId());

            clientService.findById(request.getClientId());

            request.getProductIds()
                    .stream()
                    .forEach(productId -> productService.findById(productId));

            isValid = true;
        } catch (RuntimeException ex) {
            LOGGER.error("client or some of the product do not exits!");
        }

        return isValid;
    }

    private Transaction getTransactionEntityFromRequest(TransactionApiRequest request) {
        Client client = clientService.findById(request.getClientId());
        Set<Product> products = new HashSet<>();

        request.getProductIds()
                .stream().
                forEach(productId -> products.add(productService.findById(productId)));

        Transaction entity = new Transaction();
        entity.setClient(client);
        entity.getProducts().addAll(products);
        entity.setTotalPrice(request.getTotalPrice());
        entity.setDate(request.getDate());
        entity.setStatus(request.getStatus());

        return entity;
    }
}
