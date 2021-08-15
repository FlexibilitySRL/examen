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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/paymenttype")
public class PaymentTypeController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( PaymentTypeController.class );

    @Autowired
    private PaymentTypeService service;

    @GetMapping("/all")
    public ResponseEntity<List<PaymentTypeDTO>> getAllPaymentType()
    {
        logger.info("getAllPaymentType :: IN");

        try
        {
            return new ResponseEntity<>( service.findAll(), HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
