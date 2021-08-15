package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.logic.mapper.PaymentTypeMapper;
import ar.com.plug.examen.logic.mapper.TransactionStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( TransactionController.class );

    @Autowired
    private TransactionService service;

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransaction()
    {
        logger.info("getAllTransaction :: IN");

        try
        {

        }
        catch ( Exception e )
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new ResponseEntity<>( service.findAll(), HttpStatus.OK );
    }

    @GetMapping("/allbypaymenttype/{id}")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionByPaymentType( @PathVariable long id )
    {
        logger.info("getAllTransactionByPaymentType :: IN");

        try
        {
            return new ResponseEntity<>( service.findAllByPaymentType( PaymentTypeMapper.mapIdToEntity( id ) ),
                                         HttpStatus.OK );
        }
        catch ( Exception e )
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping("/allbytransactionstatus/{id}")
    public ResponseEntity<List<TransactionDTO>> allByTransactionStatus( @PathVariable long id )
    {
        logger.info("getAllTransactionByPaymentType :: IN");

        try
        {
            return new ResponseEntity<>( service.findAllByTransactionStatus( TransactionStatusMapper.mapIdToEntity( id ) ),
                                         HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
