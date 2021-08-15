package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.UserDTO;
import ar.com.plug.examen.domain.entity.User;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    UserService
 * Description:             Interface for handling service layer of User's Entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public interface UserService
{
    List<UserDTO> findAll();
    UserDTO findById( User user ) throws FlexiNotFoundException;
}
