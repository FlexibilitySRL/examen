package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.BuyerDTO;
import ar.com.plug.examen.domain.service.BuyerService;
import ar.com.plug.examen.logic.mapper.BuyerMapper;
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
 * Name:                   BuyerController
 * Description:            Class that serves as proxy for all services available for the Buyer entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@RestController
@RequestMapping("/buyer")
public class BuyerController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( BuyerController.class );

    @Autowired
    private BuyerService service;

    /**
     * Find all buyers
     *
     * @return List<BuyerDTO>
     */
    @GetMapping( "/all" )
    public ResponseEntity<List<BuyerDTO>> getAllBuyer()
    {
        logger.debug( "getAllBuyer :: IN" );

        try
        {
            final List<BuyerDTO> answer = service.findAll();

            logger.debug( "getAllBuyer :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Find a Buyer by id
     *
     * @param id -> long
     * @return BuyerDTO
     * @throws ar.com.plug.examen.logic.exception.FlexiNotFoundException
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity<BuyerDTO> getBuyerProfile( @PathVariable long id )
    {
        logger.debug( "getBuyerProfile :: IN" );

        try
        {
            final BuyerDTO answer = service.findById( BuyerMapper.mapIdToEntity( id ) );

            logger.debug( "getBuyerProfile :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Register a new Buyer
     *
     * @param dto -> BuyerDTO
     * @return BuyerDTO
     */
    @PostMapping("/register")
    public ResponseEntity<BuyerDTO> postRegisterBuyer( @RequestBody BuyerDTO dto )
    {
        logger.debug( "postRegisterBuyer :: IN" );

        try
        {
            logger.debug( "postRegisterBuyer :: OUT" );

            final BuyerDTO answer = service.register( BuyerMapper.mapRegisterDtoToEntity( dto ) );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Updates a previously registered Buyer
     *
     * @param dto -> BuyerDTO
     * @return BuyerDTO
     * @throws ar.com.plug.examen.logic.exception.FlexiNotFoundException
     */
    @PutMapping("/update")
    public ResponseEntity<BuyerDTO> putUpdateBuyer( @RequestBody BuyerDTO dto )
    {
        logger.debug( "putUpdateBuyer :: IN" );

        try
        {
            final BuyerDTO answer = service.update( BuyerMapper.mapUpdateDtoToEntity( dto ) );

            logger.debug( "putUpdateBuyer :: OUT" );

            return new ResponseEntity<>(  answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Deletes a previously registered Buyer
     *
     * @param id -> long
     * @throws ar.com.plug.examen.logic.exception.FlexiNotFoundException
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBuyer( @PathVariable long id )
    {
        logger.debug( "deleteBuyer :: IN" );

        try
        {
            service.delete( BuyerMapper.mapIdToEntity( id ) );

            logger.debug( "deleteBuyer :: OUT" );

            return new ResponseEntity<>( HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }
}