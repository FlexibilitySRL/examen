package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.entity.EntityFactory;
import ar.com.plug.examen.domain.entity.Seller;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   SellerMapper
 * Description:            Class for handling mapping between Dto´s and Seller Entity classes
 *
 * @author teixbr
 * @version 1.0
 * @since 15/08/21
 */
public class SellerMapper extends BaseMapper
{
    public static List<SellerDTO> mapSimpleEntityToDtoList( List<Seller> entityList )
    {
        return new ArrayList<SellerDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( Seller entity : entityList )
                {
                    add( mapEntityToDto( entity ) );
                }
            }
        }};
    }

    public static SellerDTO mapEntityToDto( Seller entity )
    {
        final SellerDTO answer = DTOFactory.createSellerDTO( entity.getId() );

        answer.setFullName( entity.getFullName() );
        answer.setContactEmail( entity.getContactEmail() );
        answer.setTransactionList( TransactionMapper.mapEntityToDtoList( entity.getTransactionList() ) );

        return answer;
    }

    public static Seller mapIdToEntity( long id )
    {
        BaseMapper.validate( id, "sellerId" );

        return EntityFactory.createSeller( id );
    }

    public static Seller mapRegisterDtoToEntity( SellerDTO dto )
    {
        final Seller answer = EntityFactory.createSeller();

        validateRegister( dto );

        answer.setContactEmail( dto.getContactEmail() );
        answer.setFullName( dto.getFullName() );

        return answer;
    }

    private static void validateRegister( SellerDTO dto )
    {
        BuyerMapper.validateEmail( dto.getContactEmail() );
        BaseMapper.isBlank( dto.getFullName(), "fullName" );
    }

    public static Seller mapUpdateDtoToEntity( SellerDTO dto )
    {
        final Seller answer = EntityFactory.createSeller( dto.getId() );

        validateRegister( dto );

        answer.setContactEmail( dto.getContactEmail() );
        answer.setFullName( dto.getFullName() );

        return answer;
    }

    public static Seller mapUpdateEntityToEntity( Seller dbEntity, Seller incoming )
    {
        dbEntity.setContactEmail( incoming.getContactEmail() );
        dbEntity.setFullName( incoming.getFullName() );

        return dbEntity;
    }
}
