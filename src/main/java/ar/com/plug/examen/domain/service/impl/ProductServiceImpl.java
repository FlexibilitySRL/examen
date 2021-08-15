package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.ProductRepository;
import ar.com.plug.examen.dao.SellerRepository;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.entity.Product;
import ar.com.plug.examen.domain.entity.Seller;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    ProductServiceImpl
 * Description:             Class that will handle all DB access and business logic of the Product Entity class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Service
public class ProductServiceImpl implements ProductService
{
    private static final Logger logger = LoggerFactory.getLogger( ProductServiceImpl.class );

    @Autowired
    private ProductRepository repository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<ProductDTO> findAll()
    {
        logger.debug( "findAll :: IN" );

        List<ProductDTO> list = ProductMapper.mapEntityToDtoList( repository.findAll() );

        logger.debug( "findAll :: OUT" );

        return list;
    }

    @Override
    public ProductDTO findById( Product user )
    {
        logger.debug( "findById :: IN" );

        ProductDTO answer = ProductMapper.mapEntityToDto( repository.findById( user.getId() )
                                                                    .orElseThrow( FlexiNotFoundException::new ) );

        logger.debug( "findById :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public ProductDTO register( Product product )
    {
        logger.debug( "register :: IN" );

        logger.debug( "register :: searching seller" );

        Seller sellerDB = sellerRepository.findById( product.getSeller().getId()
                                                        ).orElseThrow( FlexiNotFoundException::new );

        product.setSeller( sellerDB );

        logger.debug( "register :: seller found -> saving product" );

        ProductDTO answer = ProductMapper.mapEntityToDto( repository.save( product ) );

        logger.debug( "register :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public ProductDTO update( Product product )
    {
        logger.debug( "update :: IN" );

        Product entityDB = repository.findById( product.getId() ).orElseThrow( FlexiNotFoundException::new );

        logger.debug( "update :: found Product -> updating attributes" );

        if ( entityDB.getSeller().getId() != product.getSeller().getId() )
        {
            logger.debug( "update :: searching seller" );

            Seller sellerDB = sellerRepository.findById( product.getSeller().getId() )
                    .orElseThrow( FlexiNotFoundException::new );

            product.setSeller( sellerDB );

            logger.debug( "update :: seller found" );
        }

        logger.debug( "update :: saving product" );

        ProductDTO answer = ProductMapper.mapEntityToDto( repository.save(
                ProductMapper.mapUpdateEntityToEntity( entityDB, product ) ) );

        logger.debug( "update :: OUT" );

        return answer;
    }

    @Override
    @Transactional
    public void delete( Product product )
    {
        logger.debug("delete :: IN");

        Product entity = repository.findById( product.getId() ).orElseThrow( FlexiNotFoundException::new );

        logger.debug( "delete :: found Product -> deleting" );

        repository.delete( entity );

        logger.debug("delete :: OUT");
    }
}