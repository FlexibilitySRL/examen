package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.ProductRepository;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.entity.Product;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<ProductDTO> findAll()
    {
        logger.info("findAll :: IN");

        List<ProductDTO> list = ProductMapper.mapSimpleEntityToDtoList( repository.findAll() );

        logger.info("findAll :: OUT");

        return list;
    }

    @Override
    public ProductDTO findById( Product user )
    {
        logger.info("findById :: IN");

        ProductDTO answer = ProductMapper.mapDetailedEntityToDto( repository.findById( user.getId() )
                                                                    .orElseThrow( FlexiNotFoundException::new ) );

        logger.info("findById :: OUT");

        return answer;
    }
}