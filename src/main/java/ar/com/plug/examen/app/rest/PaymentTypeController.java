package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.PaymentTypeDTO;
import ar.com.plug.examen.domain.service.PaymentTypeService;
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
 * Name:                   PaymentTypeController
 * Description:            Class that serves as proxy for all services available for the PaymentType entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@RestController
@RequestMapping("/paymenttype")
public class PaymentTypeController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( PaymentTypeController.class );

    @Autowired
    private PaymentTypeService service;

    /**
     * Find all buyers
     *
     * @return List<PaymentTypeDTO>
     */
    @GetMapping("/all")
    public ResponseEntity<List<PaymentTypeDTO>> getAllPaymentType()
    {
        logger.debug( "getAllPaymentType :: IN" );

        try
        {
            final List<PaymentTypeDTO> answer = service.findAll();

            logger.debug( "getAllPaymentType :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }
}
