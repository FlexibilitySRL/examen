package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.logic.mapper.ProductMapper;
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
 * Name:                   ProductController
 * Description:            Class that serves as proxy for all services available for the Product entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( ProductController.class );

    @Autowired
    private ProductService service;

    /**
     * Find all products
     *
     * @return List<ProductDTO>
     */
    @GetMapping( "/all" )
    public ResponseEntity<List<ProductDTO>> getAllProduct()
    {
        logger.debug( "getAllProduct :: IN" );

        try
        {
            final List<ProductDTO> answer = service.findAll();

            logger.debug( "getAllProduct :: OUT" );

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
    public ResponseEntity<ProductDTO> getProductProfile( @PathVariable long id )
    {
        logger.debug( "getProductProfile :: IN" );

        try
        {
            final ProductDTO answer = service.findById( ProductMapper.mapIdToEntity( id ) );

            logger.debug( "getProductProfile :: OUT" );

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
    public ResponseEntity<ProductDTO> postRegisterProduct( @RequestBody ProductDTO dto )
    {
        logger.debug( "postRegisterProduct :: IN" );

        try
        {
            final ProductDTO answer = service.register( ProductMapper.mapRegisterDtoToEntity( dto ) );

            logger.debug( "postRegisterProduct :: OUT" );

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
    public ResponseEntity<ProductDTO> putUpdateProduct( @RequestBody ProductDTO dto )
    {
        logger.debug( "putUpdateProduct :: IN" );

        try
        {
            final ProductDTO answer = service.update( ProductMapper.mapUpdateDtoToEntity( dto ) );

            logger.debug( "putUpdateProduct :: OUT" );

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
    public ResponseEntity<?> deleteProduct( @PathVariable long id )
    {
        logger.debug( "deleteProduct :: IN" );

        try
        {
            service.delete( ProductMapper.mapIdToEntity( id ) );

            logger.debug( "deleteProduct :: OUT" );

            return new ResponseEntity<>( HttpStatus.OK );
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw e;
        }
    }
}
