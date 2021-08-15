package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.UserRepository;
import ar.com.plug.examen.domain.dto.UserDTO;
import ar.com.plug.examen.domain.entity.User;
import ar.com.plug.examen.domain.service.UserService;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    UserServiceImpl
 * Description:             Class that will handle all DB access and business logic of the User Entity class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Service
public class UserServiceImpl implements UserService
{
    private static final Logger logger = LoggerFactory.getLogger( UserServiceImpl.class );

    @Autowired
    private UserRepository repository;

    @Override
    public List<UserDTO> findAll()
    {
        logger.info("findAll :: IN");

        List<UserDTO> list = UserMapper.mapSimpleEntityToDtoList( repository.findAll() );

        logger.info("findAll :: OUT");

        return list;
    }

    @Override
    public UserDTO findById( User user ) throws FlexiNotFoundException
    {
        logger.info("findById :: IN");

        UserDTO answer = UserMapper.mapDetailedEntityToDto( repository.findById( user.getId() )
                                                            .orElseThrow( FlexiNotFoundException::new ) );

        logger.info("findById :: OUT");

        return answer;
    }
}