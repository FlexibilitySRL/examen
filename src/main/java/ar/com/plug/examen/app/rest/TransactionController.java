package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.logic.mapper.PaymentTypeMapper;
import ar.com.plug.examen.logic.mapper.TransactionMapper;
import ar.com.plug.examen.logic.mapper.TransactionStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   TransactionController
 * Description:            Class that serves as proxy for all services available for the Transaction entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( TransactionController.class );

    @Autowired
    private TransactionService service;

    /**
     * Find all transaction
     *
     * @return List<TransactionDTO>
     */
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransaction()
    {
        logger.debug( "getAllTransaction :: IN" );

        try
        {
            final List<TransactionDTO> answer = service.findAll();

            logger.debug( "getAllTransaction :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Find all transaction by PaymentType
     *
     * @param id -> long
     * @return List<TransactionDTO>
     */
    @GetMapping("/allbypaymenttype/{id}")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionByPaymentType( @PathVariable long id )
    {
        logger.debug( "getAllTransactionByPaymentType :: IN" );

        try
        {
            final List<TransactionDTO> answer = service.findAllByPaymentType( PaymentTypeMapper.mapIdToEntity( id ) );

            logger.debug( "getAllTransactionByPaymentType :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Find all transaction by TransactionStatus
     *
     * @param id -> long
     * @return List<TransactionDTO>
     */
    @GetMapping("/allbytransactionstatus/{id}")
    public ResponseEntity<List<TransactionDTO>> allByTransactionStatus( @PathVariable long id )
    {
        logger.debug( "getAllTransactionByPaymentType :: IN" );

        try
        {
            final List<TransactionDTO> answer = service.findAllByTransactionStatus(
                                                        TransactionStatusMapper.mapIdToEntity( id ) );

            logger.debug( "getAllTransactionByPaymentType :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Register a new Transaction
     *
     * @param dto -> TransactionDTO
     * @return TransactionDTO
     *
     * @throws ar.com.plug.examen.logic.exception.FlexiNotFoundException,
     *          ar.com.plug.examen.logic.exception.FlexiNotEnoughStockException,
     *          ar.com.plug.examen.logic.exception.FlexiAmountsDoesntMatchException
     */
    @PostMapping("/register")
    public ResponseEntity<TransactionDTO> postRegisterTransaction( @RequestBody TransactionDTO dto )
    {
        logger.debug( "postRegisterTransaction :: IN" );

        try
        {
            final TransactionDTO answer = service.register( TransactionMapper.mapRegisterDtoToEntity( dto ) );

            logger.debug( "postRegisterTransaction :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Approves a previously registered Transaction
     *
     * @param id -> long
     * @return TransactionDTO
     * @throws ar.com.plug.examen.logic.exception.FlexiInvalidOperationException
     */
    @PutMapping("/approve/{id}")
    public ResponseEntity<TransactionDTO> putApproveTransaction( @PathVariable long id )
    {
        logger.debug( "putApproveTransaction :: IN" );

        try
        {
            final TransactionDTO answer = service.approve( TransactionMapper.mapIdToEntity( id ) );

            logger.debug( "putApproveTransaction :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Rejects a previously registered Transaction
     *
     * @param id -> long
     * @return TransactionDTO
     * @throws ar.com.plug.examen.logic.exception.FlexiInvalidOperationException
     */
    @PutMapping("/reject/{id}")
    public ResponseEntity<TransactionDTO> putRejectTransaction( @PathVariable long id )
    {
        logger.debug( "putRejectTransaction :: IN" );

        try
        {
            final TransactionDTO answer = service.reject( TransactionMapper.mapIdToEntity( id ) );

            logger.debug( "putRejectTransaction :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Deletes a previously registered Transaction
     *
     * @param id -> long
     * @throws ar.com.plug.examen.logic.exception.FlexiNotFoundException
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction( @PathVariable long id )
    {
        logger.debug( "deleteTransaction :: IN" );

        try
        {
            service.delete( TransactionMapper.mapIdToEntity( id ) );

            logger.debug( "deleteTransaction :: OUT" );

            return new ResponseEntity<>( HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }
}
