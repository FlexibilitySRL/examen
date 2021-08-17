package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.TransactionStatusDTO;
import ar.com.plug.examen.domain.entity.EntityFactory;
import ar.com.plug.examen.domain.entity.TransactionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   TransactionStatusMapper
 * Description:            Class for handling mapping between Dto´s and TransactionStatus Entity classes
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class TransactionStatusMapper extends BaseMapper
{
    public static List<TransactionStatusDTO> mapEntityToDtoList( List<TransactionStatus> entityList )
    {
        return new ArrayList<TransactionStatusDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( TransactionStatus entity : entityList )
                {
                    add( mapEntityToDto( entity ) );
                }
            }
        }};
    }

    public static TransactionStatusDTO mapEntityToDto( TransactionStatus entity )
    {
        final TransactionStatusDTO answer = DTOFactory.createTransactionStatusDTO( entity.getId() );

        answer.setName( entity.getName() );

        return answer;
    }

    public static TransactionStatus mapIdToEntity( long id )
    {
        BaseMapper.validate( id, "transactionStatusId" );

        return EntityFactory.createTransactionStatus( id );
    }
}