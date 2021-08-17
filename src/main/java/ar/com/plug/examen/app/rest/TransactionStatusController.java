package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.TransactionStatusDTO;
import ar.com.plug.examen.domain.service.TransactionStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   TransactionStatusController
 * Description:            Class that serves as proxy for all services available for the TransactionStatus entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@RestController
@RequestMapping("/transactionstatus")
public class TransactionStatusController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( TransactionStatusController.class );

    @Autowired
    private TransactionStatusService service;

    /**
     * Find all transaction status
     *
     * @return List<TransactionStatusDTO>
     */
    @GetMapping( "/all" )
    public ResponseEntity<List<TransactionStatusDTO>> getAllTransactionStatus()
    {
        logger.debug( "getAllTransactionStatus :: IN" );

        try
        {
            final List<TransactionStatusDTO> answer = service.findAll();

            logger.debug( "getAllTransactionStatus :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }
}
