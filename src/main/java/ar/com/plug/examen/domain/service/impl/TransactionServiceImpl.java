package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.BuyerRepository;
import ar.com.plug.examen.dao.PaymentTypeRepository;
import ar.com.plug.examen.dao.ProductRepository;
import ar.com.plug.examen.dao.SellerRepository;
import ar.com.plug.examen.dao.TransactionRepository;
import ar.com.plug.examen.dao.TransactionStatusRepository;
import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.entity.PaymentType;
import ar.com.plug.examen.domain.entity.Transaction;
import ar.com.plug.examen.domain.entity.TransactionDetail;
import ar.com.plug.examen.domain.entity.TransactionStatus;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.logic.exception.FlexiAmountsDoesntMatchException;
import ar.com.plug.examen.logic.exception.FlexiInvalidOperationException;
import ar.com.plug.examen.logic.exception.FlexiNotEnoughStockException;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.mapper.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    TransactionServiceImpl
 * Description:             Class that will handle all DB access and business logic of the Transaction Entity class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Service
public class TransactionServiceImpl implements TransactionService
{
    private static final Logger logger = LoggerFactory.getLogger( TransactionServiceImpl.class );

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private TransactionStatusRepository statusRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<TransactionDTO> findAll()
    {
        logger.debug( "findAll :: IN" );

        List<TransactionDTO> list = TransactionMapper.mapEntityToDtoList( repository.findAll() );

        logger.debug( "findAll :: OUT" );

        return list;
    }

    @Override
    public List<TransactionDTO> findAllByPaymentType( PaymentType paymentType )
    {
        logger.debug( "findAllByPaymentType :: IN" );

        List<TransactionDTO> list = TransactionMapper.mapEntityToDtoList(
                                                        repository.getAllByPaymentType( paymentType ) );

        logger.debug( "findAllByPaymentType :: OUT" );

        return list;
    }

    @Override
    public List<TransactionDTO> findAllByTransactionStatus( TransactionStatus transactionStatus )
    {
        logger.debug( "findAllByTransactionStatus :: IN" );

        List<TransactionDTO> list = TransactionMapper.mapEntityToDtoList(
                repository.getAllByTransactionStatus( transactionStatus ) );

        logger.debug( "findAllByTransactionStatus :: OUT" );

        return list;
    }

    @Override
    @Transactional
    public TransactionDTO register( Transaction transaction )
    {
        logger.debug( "register :: IN -> searching seller" );

        transaction.setSeller( sellerRepository.findById( transaction.getSeller().getId() )
                                       .orElseThrow( FlexiNotFoundException::new ) );

        logger.debug( "register :: seller found -> searching buyer" );

        transaction.setBuyer( buyerRepository.findById( transaction.getBuyer().getId() )
                                      .orElseThrow( FlexiNotFoundException::new ) );

        logger.debug( "register :: buyer found -> searching status" );

        transaction.setTransactionStatus( statusRepository.findById( TransactionStatusEnum.PENDING.getValue() )
                                                  .orElseThrow( FlexiNotFoundException::new ) );

        logger.debug( "register :: status found -> searching paymentType" );

        transaction.setPaymentType( paymentTypeRepository.findById( transaction.getPaymentType().getId() )
                                            .orElseThrow( FlexiNotFoundException::new ) );

        logger.debug( "register :: paymentTypeDB found -> validating detail list" );

        double auxTotal = 0.0;
        
        for ( TransactionDetail detail : transaction.getTransactionDetailList() )
        {
            detail.setProduct( productRepository.findById( detail.getProduct().getId() )
                                       .orElseThrow( FlexiNotFoundException::new ) );
            detail.setTransaction( transaction );

            if ( detail.getProduct().getStock() >= detail.getQuantity() )
            {
                detail.getProduct().setStock( detail.getProduct().getStock() - detail.getQuantity() );
                productRepository.save( detail.getProduct() );
            }
            else
            {
                throw new FlexiNotEnoughStockException();
            }

            auxTotal += detail.getProduct().getPrice() * detail.getQuantity();
        }

        if ( round( auxTotal ) != transaction.getAmount() )
        {
            throw new FlexiAmountsDoesntMatchException();
        }

        transaction.setDate( LocalDateTime.now() );

        final TransactionDTO answer = TransactionMapper.mapEntityToDto( repository.save( transaction ) );

        logger.debug( "register :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public void delete( Transaction transaction )
    {
        logger.debug( "delete :: IN" );

        Transaction entity = repository.findById( transaction.getId() )
                                        .orElseThrow( FlexiNotFoundException::new );

        logger.debug( "delete :: found Transaction -> deleting" );

        repository.delete( entity );

        logger.debug( "delete :: OUT" );
    }

    @Override
    @Transactional
    public TransactionDTO approve( Transaction transaction )
    {
        logger.debug("approve :: IN");

        Transaction entityDB = repository.findById( transaction.getId() )
                .orElseThrow( FlexiNotFoundException::new );

        logger.debug( "approve :: found Transaction -> approving" );

        if ( entityDB.getTransactionStatus().getId() == TransactionStatusEnum.PENDING.getValue() )
        {
            entityDB.setTransactionStatus( statusRepository.findById( TransactionStatusEnum.APPROVED.getValue() )
                                                      .orElseThrow( FlexiNotFoundException::new ) );

            logger.debug( "approve :: saving" );

            final TransactionDTO answer = TransactionMapper.mapEntityToDto( repository.save( entityDB ) );

            logger.debug( "reject :: OUT" );

            return answer;
        }
        else
        {
            logger.debug( "approve :: OUT" );

            throw new FlexiInvalidOperationException();
        }
    }

    @Override
    @Transactional
    public TransactionDTO reject( Transaction transaction )
    {
        logger.debug("reject :: IN");

        Transaction entityDB = repository.findById( transaction.getId() )
                .orElseThrow( FlexiNotFoundException::new );

        logger.debug( "reject :: found Transaction -> rejecting" );

        if ( entityDB.getTransactionStatus().getId() == TransactionStatusEnum.PENDING.getValue() )
        {
            entityDB.setTransactionStatus( statusRepository.findById( TransactionStatusEnum.REJECTED.getValue() )
                                                      .orElseThrow( FlexiNotFoundException::new ) );

            logger.debug( "reject :: saving" );

            final TransactionDTO answer = TransactionMapper.mapEntityToDto( repository.save( entityDB ) );

            logger.debug( "reject :: OUT" );

            return answer;
        }
        else
        {
            logger.debug( "reject :: OUT" );

            throw new FlexiInvalidOperationException();
        }
    }

    private static double round( double value )
    {
        BigDecimal bd = BigDecimal.valueOf( value);
        bd = bd.setScale( 2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }
}
