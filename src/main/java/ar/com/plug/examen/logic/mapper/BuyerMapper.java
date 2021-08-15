package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.BuyerDTO;
import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.entity.Buyer;
import ar.com.plug.examen.domain.entity.EntityFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   BuyerMapper
 * Description:            Class for handling mapping between Dto´s and Buyer Entity classes
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class BuyerMapper extends BaseMapper
{
    public static List<BuyerDTO> mapSimpleEntityToDtoList( List<Buyer> entityList )
    {
        return new ArrayList<BuyerDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( Buyer entity : entityList )
                {
                    add( mapSimpleEntityToDto( entity ) );
                }
            }
        }};
    }

    public static BuyerDTO mapSimpleEntityToDto( Buyer entity )
    {
        final BuyerDTO answer = DTOFactory.createBuyerDTO( entity.getId() );

        answer.setDocument( entity.getDocument() );
        answer.setName( entity.getName() );
        answer.setLastName( entity.getLastName() );
        answer.setEmail( entity.getEmail() );

        return answer;
    }

    public static BuyerDTO mapDetailedEntityToDto( Buyer entity )
    {
        final BuyerDTO answer = DTOFactory.createBuyerDTO( entity.getId() );

        answer.setDocument( entity.getDocument() );
        answer.setName( entity.getName() );
        answer.setLastName( entity.getLastName() );
        answer.setEmail( entity.getEmail() );
        answer.setTransactionList( TransactionMapper.mapEntityToDtoList( entity.getTransactionList() ) );

        return answer;
    }

    public static String mapBuyerFullName( Buyer entity )
    {
        return entity.getName() + " " + entity.getLastName();
    }

    public static Buyer mapIdToEntity( long id )
    {
        BaseMapper.validate( id, "buyerId" );

        return EntityFactory.createBuyer( id );
    }

    public static Buyer mapAttributes( BuyerDTO dto, Buyer answer )
    {
        answer.setEmail( dto.getEmail() );
        answer.setLastName( dto.getLastName() );
        answer.setName( dto.getName() );
        answer.setDocument( dto.getDocument() );

        return answer;
    }

    public static Buyer mapRegisterDtoToEntity( BuyerDTO dto )
    {
        final Buyer answer = EntityFactory.createBuyer();

        validateRegister( dto );
        mapAttributes( dto, answer );

        return answer;
    }

    private static void validateRegister( BuyerDTO dto )
    {
        BaseMapper.validateEmail( dto.getEmail() );
        BaseMapper.isBlank( dto.getName(), "name" );
        BaseMapper.isBlank( dto.getLastName(), "lastName" );
        BaseMapper.isBlank( dto.getDocument(), "document" );
    }

    public static Buyer mapUpdateDtoToEntity( BuyerDTO dto )
    {
        final Buyer answer = EntityFactory.createBuyer( dto.getId() );

        validateRegister( dto );
        mapAttributes( dto, answer );

        return answer;
    }

    public static Buyer mapUpdateEntityToEntity( Buyer dbEntity, Buyer incoming )
    {
        dbEntity.setEmail( incoming.getEmail() );
        dbEntity.setLastName( incoming.getLastName() );
        dbEntity.setName( incoming.getName() );
        dbEntity.setDocument( incoming.getDocument() );

        return dbEntity;
    }
}
