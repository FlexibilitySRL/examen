package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.Status;
import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.app.api.VendorApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.service.TransactionService;
import ar.com.flexibility.examen.domain.service.VendorService;
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
@RequestMapping(path = "/vendors", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendorController {

    @Autowired
    private VendorService vendorService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<List<VendorApi>> listVendors() {
        log.trace("Request to GET all the vendors");
        return ResponseEntity.status(HttpStatus.OK).body(vendorService.all());
    }

    @GetMapping(value = "/{vendorId}")
    public ResponseEntity<VendorApi> getVendor(@PathVariable Long vendorId) {
        log.trace("Request to GET vendorId: {}", vendorId);
        return ResponseEntity.status(HttpStatus.OK).body(vendorService.get(vendorId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VendorApi> createVendor(@Valid @RequestBody VendorApi vendorApi) {
        log.trace("Request to POST new vendor: {}", vendorApi);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorService.create(vendorApi));
    }

    @PutMapping(value = "/{vendorId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VendorApi> updateVendors(@PathVariable Long vendorId, @Valid @RequestBody VendorApi vendorApi) {
        log.trace("Request to PUT vendor with vendorId {} with vendor: {}", vendorId, vendorApi);
        return ResponseEntity.status(HttpStatus.OK).body(vendorService.update(vendorId, vendorApi));
    }

    @DeleteMapping(value = "/{vendorId}")
    public ResponseEntity<?> removeVendors(@PathVariable Long vendorId) {
        log.trace("Request to DELETE vendor with vendorId {}", vendorId);
        vendorService.remove(vendorId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/{vendorId}/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionApi> createTransaction(@PathVariable Long vendorId,
                                                            @Valid @RequestBody TransactionApi transactionApi) {
        log.trace("Request to POST a transaction for vendor vendorId {} with transaction details: {}", vendorId, transactionApi);
        if (!transactionApi.getStatus().equals(Status.PENDING))
            throw new BadRequestException("The only possible status a new transaction is PENDING");
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.create(vendorId, transactionApi));
    }

    @GetMapping(value = "/{vendorId}/transactions")
    public ResponseEntity<List<TransactionApi>> allTransactionsForVendorId(@PathVariable Long vendorId) {
        log.trace("Request to GET all transaction for vendor vendorId: {}", vendorId);
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.allByVendor(vendorId));
    }

    @PutMapping(value = "/{vendorId}/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionApi> updateTransaction(@PathVariable Long vendorId,
                                                            @Valid @RequestBody TransactionApi transactionApi) {
        log.trace("Request to PUT a transaction with vendorId: {}, with transaction {}", vendorId, transactionApi);
        if (transactionApi.getStatus().equals(Status.PENDING))
            throw new BadRequestException("The only possible status for a PENDING transaction is APPROVED or DENIED");
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.updateStatus(vendorId, transactionApi));
    }
}
