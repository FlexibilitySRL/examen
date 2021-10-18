package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("register")
    @ApiOperation("Registrar transacción: Las transacciones quedan con un estado 'P' (Pendiente) y deben ser aprobadas")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<TransactionDTO> save(@RequestBody TransactionDTO transaction) {

        return new ResponseEntity<>(transactionService.save(transaction),
                HttpStatus.CREATED);
    }

    @PatchMapping("/approve/{id}")
    @ApiOperation("Aprobar una transacción identificada con el id suministrado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
    })
    public ResponseEntity<TransactionDTO> approve(
            @ApiParam(value = "id de la transacción que se quiere aprobar", required = true)
            @PathVariable("id") long id){

        return transactionService.findTransactionById(id)
                     .map(transactionDTO ->
                         new ResponseEntity<>(transactionService.approveTransaction(transactionDTO),HttpStatus.OK))
                     .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar una transacción por su ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
    })
    public ResponseEntity<TransactionDTO> findTransactionById(
            @ApiParam(value = "Id de la transacción que se desea consultar", required = true)
            @PathVariable("id") long id){

       return transactionService.findTransactionById(id)
               .map(transactionDTO -> new ResponseEntity<>(transactionDTO, HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/approved")
    @ApiOperation("Buscar todas las transacciones aprobadas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 200, message = "NOT FOUND")
    })
    public ResponseEntity<List<TransactionDTO>> findApprovedTransaction() {

       return transactionService.findApprovedTransactions()
                .map(transactionsList -> new ResponseEntity<>(transactionsList, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
