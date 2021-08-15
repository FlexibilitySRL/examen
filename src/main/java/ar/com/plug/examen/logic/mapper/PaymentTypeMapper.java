package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.PaymentTypeDTO;
import ar.com.plug.examen.domain.entity.EntityFactory;
import ar.com.plug.examen.domain.entity.PaymentType;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   PaymentTypeMapper
 * Description:            Class for handling mapping between Dto´s and PaymentType Entity classes
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class PaymentTypeMapper extends BaseMapper
{
    public static List<PaymentTypeDTO> mapEntityToDtoList( List<PaymentType> entityList )
    {
        return new ArrayList<PaymentTypeDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( PaymentType entity : entityList )
                {
                    add( mapEntityToDto( entity ) );
                }
            }
        }};
    }

    public static PaymentTypeDTO mapEntityToDto( PaymentType entity )
    {
        final PaymentTypeDTO answer = DTOFactory.createPaymentTypeDTO( entity.getId() );

        answer.setName( entity.getName() );

        return answer;
    }

    public static PaymentType mapIdToEntity( long id )
    {
        BaseMapper.validate( id, "paymentTypeId" );

        return EntityFactory.createPaymentType( id );
    }
}
