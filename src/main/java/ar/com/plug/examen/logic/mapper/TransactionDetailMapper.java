package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.TransactionDetailDTO;
import ar.com.plug.examen.domain.entity.TransactionDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   TransactionDetailMapper
 * Description:            Class for handling mapping between Dto´s and TransactionDetail Entity classes
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class TransactionDetailMapper extends BaseMapper
{
    public static List<TransactionDetailDTO> mapEntityToDtoList( List<TransactionDetail> entityList )
    {
        return new ArrayList<TransactionDetailDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( TransactionDetail entity : entityList )
                {
                    add( mapEntityToDto( entity ) );
                }
            }
        }};
    }

    public static TransactionDetailDTO mapEntityToDto( TransactionDetail entity )
    {
        final TransactionDetailDTO answer = DTOFactory.createTransactionDetailDTO( entity.getId() );

        answer.setQuantity( entity.getQuantity() );
        answer.setProduct( ProductMapper.mapSimpleEntityToDto( entity.getProduct() ) );

        return answer;
    }
}