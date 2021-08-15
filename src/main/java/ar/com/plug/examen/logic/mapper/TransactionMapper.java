package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.entity.EntityFactory;
import ar.com.plug.examen.domain.entity.Transaction;
import ar.com.plug.examen.logic.exception.FlexiIncompleteDataException;

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
        answer.setBuyerName( BuyerMapper.mapBuyerFullName( entity.getBuyer() ) );
        answer.setSellerId( entity.getSeller().getId() );
        answer.setSellerName( entity.getSeller().getFullName() );
        answer.setPaymentType( PaymentTypeMapper.mapEntityToDto( entity.getPaymentType() ) );
        answer.setDate( entity.getDate() );
        answer.setDetailList( TransactionDetailMapper.mapEntityToDtoList( entity.getTransactionDetailList() ) );

        return answer;
    }

    public static Transaction mapIdToEntity( long id )
    {
        BaseMapper.validate( id, "transactionId" );

        return EntityFactory.createTransaction( id );
    }

    public static Transaction mapRegisterDtoToEntity( TransactionDTO dto )
    {
        final Transaction answer = EntityFactory.createTransaction();

        validateRegister( dto );

        answer.setBuyer( BuyerMapper.mapIdToEntity( dto.getBuyerId() ) );
        answer.setSeller( SellerMapper.mapIdToEntity( dto.getSellerId() ) );
        answer.setPaymentType( PaymentTypeMapper.mapIdToEntity( dto.getPaymentType().getId() ) );
        answer.setAmount( dto.getAmount() );

        if ( !dto.getDetailList().isEmpty() )
        {
            answer.setTransactionDetailList( TransactionDetailMapper.mapRegisterList( dto.getDetailList() ) );
        }
        else
        {
            throw new FlexiIncompleteDataException( "detailList" );
        }

        return answer;
    }

    private static void validateRegister( TransactionDTO dto )
    {
        BaseMapper.validate( dto.getBuyerId(), "buyerId" );
        BaseMapper.validate( dto.getSellerId(), "sellerId" );
    }
}
