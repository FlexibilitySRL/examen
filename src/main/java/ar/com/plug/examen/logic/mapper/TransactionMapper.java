package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   TransactionMapper
 * Description:            Class for handling mapping between Dto´s and Transaction Entity classes
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class TransactionMapper extends BaseMapper
{
    public static List<TransactionDTO> mapEntityToDtoList( List<Transaction> entityList )
    {
        return new ArrayList<TransactionDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( Transaction entity : entityList )
                {
                    add( mapEntityToDto( entity ) );
                }
            }
        }};
    }

    public static TransactionDTO mapEntityToDto( Transaction entity )
    {
        final TransactionDTO answer = DTOFactory.createTransactionDTO( entity.getId() );

        answer.setAmount( entity.getAmount() );
        answer.setStatus( TransactionStatusMapper.mapEntityToDto( entity.getTransactionStatus() ) );
        answer.setBuyerId( entity.getBuyer().getId() );
        answer.setBuyerName( UserMapper.mapUserFullName( entity.getBuyer() ) );
        answer.setSellerId( entity.getSeller().getId() );
        answer.setSellerName( UserMapper.mapUserFullName( entity.getSeller() ) );
        answer.setPaymentType( PaymentTypeMapper.mapEntityToDto( entity.getPaymentType() ) );
        answer.setDate( entity.getDate() );
        answer.setDetailList( TransactionDetailMapper.mapEntityToDtoList( entity.getTransactionDetailList() ) );

        return answer;
    }
}
