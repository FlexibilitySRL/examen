package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.TransactionRepository;
import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.entity.PaymentType;
import ar.com.plug.examen.domain.entity.TransactionStatus;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.logic.mapper.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<TransactionDTO> findAll()
    {
        logger.info("findAll :: IN");

        List<TransactionDTO> list = TransactionMapper.mapEntityToDtoList( repository.findAll() );

        logger.info("findAll :: OUT");

        return list;
    }

    @Override
    public List<TransactionDTO> findAllByPaymentType( PaymentType paymentType )
    {
        logger.info("findAllByPaymentType :: IN");

        List<TransactionDTO> list = TransactionMapper.mapEntityToDtoList(
                                                        repository.getAllByPaymentType( paymentType ) );

        logger.info("findAllByPaymentType :: OUT");

        return list;
    }

    @Override
    public List<TransactionDTO> findAllByTransactionStatus( TransactionStatus transactionStatus )
    {
        logger.info("findAllByTransactionStatus :: IN");

        List<TransactionDTO> list = TransactionMapper.mapEntityToDtoList(
                repository.getAllByTransactionStatus( transactionStatus ) );

        logger.info("findAllByTransactionStatus :: OUT");

        return list;
    }
}
