package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.logic.mapper.SellerMapper;
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
 * Name:                   SellerController
 * Description:            Class that serves as proxy for all services available for the Seller entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@RestController
@RequestMapping("/seller")
public class SellerController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( SellerController.class );

    @Autowired
    private SellerService service;

    /**
     * Find all sellers
     *
     * @return List<ProductDTO>
     */
    @GetMapping( "/all" )
    public ResponseEntity<List<SellerDTO>> getAllSeller()
    {
        logger.debug( "getAllSeller :: IN" );

        try
        {
            List<SellerDTO> answer = service.findAll();

            logger.debug( "getAllSeller :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Find a Product by id
     *
     * @param id -> long
     * @return ProductDTO
     * @throws ar.com.plug.examen.logic.exception.FlexiNotFoundException
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity<SellerDTO> getSellerProfile( @PathVariable long id )
    {
        logger.debug( "getSellerProfile :: IN" );

        try
        {
            final SellerDTO answer = service.findById( SellerMapper.mapIdToEntity( id ) );

            logger.debug( "getSellerProfile :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Register a new Product
     *
     * @param dto -> ProductDTO
     * @return ProductDTO
     */
    @PostMapping("/register")
    public ResponseEntity<SellerDTO> postRegisterSeller( @RequestBody SellerDTO dto )
    {
        logger.debug( "postRegisterSeller :: IN" );

        try
        {
            final SellerDTO answer = service.register( SellerMapper.mapRegisterDtoToEntity( dto ) );

            logger.debug( "postRegisterSeller :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Updates a previously registered Product
     *
     * @param dto -> ProductDTO
     * @return ProductDTO
     * @throws ar.com.plug.examen.logic.exception.FlexiNotFoundException
     */
    @PutMapping("/update")
    public ResponseEntity<SellerDTO> putUpdateSeller( @RequestBody SellerDTO dto )
    {
        logger.debug( "putUpdateSeller :: IN" );

        try
        {
            final SellerDTO answer = service.update( SellerMapper.mapUpdateDtoToEntity( dto ) );

            logger.debug( "putUpdateSeller :: OUT" );

            return new ResponseEntity<>( answer, HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }

    /**
     * Deletes a previously registered Product
     *
     * @param id -> long
     * @throws ar.com.plug.examen.logic.exception.FlexiNotFoundException
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSeller( @PathVariable long id )
    {
        logger.debug( "deleteSeller :: IN" );

        try
        {
            service.delete( SellerMapper.mapIdToEntity( id ) );

            logger.debug( "deleteSeller :: OUT" );

            return new ResponseEntity<>( HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }
}