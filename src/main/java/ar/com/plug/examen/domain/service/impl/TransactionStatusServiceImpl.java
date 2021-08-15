package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.TransactionStatusRepository;
import ar.com.plug.examen.domain.dto.TransactionStatusDTO;
import ar.com.plug.examen.domain.entity.TransactionStatus;
import ar.com.plug.examen.domain.service.TransactionStatusService;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.mapper.TransactionStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    TransactionStatusServiceImpl
 * Description:             Class that will handle all DB access and business logic of the TransactionStatus Entity class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Service
public class TransactionStatusServiceImpl implements TransactionStatusService
{
    private static final Logger logger = LoggerFactory.getLogger( TransactionStatusServiceImpl.class );

    @Autowired
    private TransactionStatusRepository repository;

    @Override
    public List<TransactionStatusDTO> findAll()
    {
        logger.debug( "findAll :: IN" );

        List<TransactionStatusDTO> list = TransactionStatusMapper.mapEntityToDtoList( repository.findAll() );

        logger.debug( "findAll :: OUT" );

        return list;
    }

    @Override
    public TransactionStatusDTO findById( TransactionStatus transactionStatus )
    {
        logger.debug( "findById :: IN" );

        TransactionStatusDTO answer = TransactionStatusMapper.mapEntityToDto(
                    repository.findById( transactionStatus.getId() ).orElseThrow( FlexiNotFoundException::new ) );

        logger.debug( "findById :: OUT" );

        return answer;
    }
}