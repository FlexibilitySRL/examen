package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.UserDTO;
import ar.com.plug.examen.domain.service.UserService;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.mapper.UserMapper;
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
@RequestMapping("/user")
public class UserController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger( UserController.class );

    @Autowired
    private UserService service;

    @GetMapping( "/all" )
    public ResponseEntity<List<UserDTO>> getAllUser()
    {
        logger.info( "getAllUser :: IN" );

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

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDTO> getUserProfile( @PathVariable long id ) throws FlexiNotFoundException
    {
        logger.info("getUserProfile :: IN");

        try
        {
            return new ResponseEntity<>( service.findById( UserMapper.mapIdToEntity( id ) ),
                                         HttpStatus.OK );
        }
        catch ( FlexiNotFoundException e )
        {
            logger.error( e.toString() );
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        catch ( Exception e )
        {
            logger.error( e.toString() );
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}