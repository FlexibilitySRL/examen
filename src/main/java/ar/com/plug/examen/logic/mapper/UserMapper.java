package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.UserDTO;
import ar.com.plug.examen.domain.entity.EntityFactory;
import ar.com.plug.examen.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   UserMapper
 * Description:            Class for handling mapping between Dto´s and User Entity classes
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class UserMapper extends BaseMapper
{
    public static List<UserDTO> mapSimpleEntityToDtoList( List<User> entityList )
    {
        return new ArrayList<UserDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( User entity : entityList )
                {
                    add( mapSimpleEntityToDto( entity ) );
                }
            }
        }};
    }

    public static UserDTO mapSimpleEntityToDto( User entity )
    {
        final UserDTO answer = DTOFactory.createUserDTO( entity.getId() );

        answer.setDocument( entity.getDocument() );
        answer.setName( entity.getName() );
        answer.setLastName( entity.getLastName() );
        answer.setEmail( entity.getEmail() );

        return answer;
    }

    public static UserDTO mapDetailedEntityToDto( User entity )
    {
        final UserDTO answer = DTOFactory.createUserDTO( entity.getId() );

        answer.setDocument( entity.getDocument() );
        answer.setName( entity.getName() );
        answer.setLastName( entity.getLastName() );
        answer.setEmail( entity.getEmail() );
        answer.setProductList( ProductMapper.mapSimpleEntityToDtoList( entity.getProductList() ) );
        answer.setSellingTransactionList( TransactionMapper.mapEntityToDtoList( entity.getSellingTransactionList() ) );
        answer.setBuyingTransactionList( TransactionMapper.mapEntityToDtoList( entity.getBuyingTransactionList() ) );

        return answer;
    }

    public static String mapUserFullName( User entity )
    {
        return entity.getName() + " " + entity.getLastName();
    }

    public static User mapIdToEntity( long id )
    {
        BaseMapper.validate( id );

        return EntityFactory.createUser( id );
    }
}
