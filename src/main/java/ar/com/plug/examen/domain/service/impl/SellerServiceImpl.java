package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.SellerRepository;
import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.entity.Seller;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.mapper.SellerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    SellerServiceImpl
 * Description:             Class that will handle all DB access and business logic of the Seller Entity class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Service
public class SellerServiceImpl implements SellerService
{
    private static final Logger logger = LoggerFactory.getLogger( SellerServiceImpl.class );

    @Autowired
    private SellerRepository repository;

    @Override
    public List<SellerDTO> findAll()
    {
        logger.debug( "findAll :: IN" );

        List<SellerDTO> list = SellerMapper.mapSimpleEntityToDtoList( repository.findAll() );

        logger.debug( "findAll :: OUT" );

        return list;
    }

    @Override
    public SellerDTO findById( Seller seller )
    {
        logger.debug( "findById :: IN" );

        SellerDTO answer = SellerMapper.mapEntityToDto( repository.findById( seller.getId() )
                                                      .orElseThrow( FlexiNotFoundException::new ) );

        logger.debug( "findById :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public SellerDTO register( Seller seller )
    {
        logger.debug( "register :: IN" );

        SellerDTO answer = SellerMapper.mapEntityToDto( repository.save( seller ) );

        logger.debug( "register :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public SellerDTO update( Seller seller )
    {
        logger.debug( "update :: IN" );

        Seller entityDB = repository.findById( seller.getId() ).orElseThrow( FlexiNotFoundException::new );

        logger.debug( "update :: found Seller -> updating attributes" );

        SellerDTO answer = SellerMapper.mapEntityToDto( repository.save(
                SellerMapper.mapUpdateEntityToEntity( entityDB, seller ) ) );

        logger.debug( "update :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public void delete( Seller seller )
    {
        logger.debug( "delete :: IN" );

        Seller entity = repository.findById( seller.getId() ).orElseThrow( FlexiNotFoundException::new );

        logger.debug( "delete :: found Seller -> deleting" );

        repository.delete( entity );

        logger.debug( "delete :: OUT" );
    }
}