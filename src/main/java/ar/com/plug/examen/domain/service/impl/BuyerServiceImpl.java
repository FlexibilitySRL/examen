package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.BuyerRepository;
import ar.com.plug.examen.domain.dto.BuyerDTO;
import ar.com.plug.examen.domain.entity.Buyer;
import ar.com.plug.examen.domain.service.BuyerService;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.mapper.BuyerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    BuyerServiceImpl
 * Description:             Class that will handle all DB access and business logic of the Buyer Entity class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Service
public class BuyerServiceImpl implements BuyerService
{
    private static final Logger logger = LoggerFactory.getLogger( BuyerServiceImpl.class );

    @Autowired
    private BuyerRepository repository;

    @Override
    public List<BuyerDTO> findAll()
    {
        logger.debug( "findAll :: IN" );

        List<BuyerDTO> list = BuyerMapper.mapSimpleEntityToDtoList( repository.findAll() );

        logger.debug( "findAll :: OUT" );

        return list;
    }

    @Override
    public BuyerDTO findById( Buyer buyer )
    {
        logger.debug( "findById :: IN" );

        BuyerDTO answer = BuyerMapper.mapDetailedEntityToDto( repository.findById( buyer.getId() )
                                                            .orElseThrow( FlexiNotFoundException::new ) );

        logger.debug( "findById :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public BuyerDTO register( Buyer buyer )
    {
        logger.debug( "register :: IN" );

        BuyerDTO answer = BuyerMapper.mapDetailedEntityToDto( repository.save( buyer ) );

        logger.debug( "register :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public BuyerDTO update( Buyer buyer )
    {
        logger.debug( "update :: IN" );

        Buyer entityDB = repository.findById( buyer.getId() ).orElseThrow( FlexiNotFoundException::new );

        logger.debug( "update :: found Buyer -> updating attributes" );

        BuyerDTO answer = BuyerMapper.mapDetailedEntityToDto( repository.save(
                                    BuyerMapper.mapUpdateEntityToEntity( entityDB, buyer ) ) );

        logger.debug("update :: OUT");

        return answer;
    }

    @Override
    @Transactional
    public void delete( Buyer buyer )
    {
        logger.debug( "delete :: IN" );

        Buyer entity = repository.findById( buyer.getId() ).orElseThrow( FlexiNotFoundException::new );

        logger.debug( "delete :: found Buyer -> deleting" );

       repository.delete( entity );

        logger.debug( "delete :: OUT" );
    }
}