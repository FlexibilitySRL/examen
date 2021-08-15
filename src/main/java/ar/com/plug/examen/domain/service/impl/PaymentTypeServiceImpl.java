package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.dao.PaymentTypeRepository;
import ar.com.plug.examen.domain.dto.PaymentTypeDTO;
import ar.com.plug.examen.domain.service.PaymentTypeService;
import ar.com.plug.examen.logic.mapper.PaymentTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    PaymentTypeServiceImpl
 * Description:             Class that will handle all DB access and business logic of the PaymentType Entity class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Service
public class PaymentTypeServiceImpl implements PaymentTypeService
{
    private static final Logger logger = LoggerFactory.getLogger( PaymentTypeServiceImpl.class );

    @Autowired
    private PaymentTypeRepository repository;

    @Override
    public List<PaymentTypeDTO> findAll()
    {
        logger.info("findAll :: IN");

        List<PaymentTypeDTO> list = PaymentTypeMapper.mapEntityToDtoList( repository.findAll() );

        logger.info("findAll :: OUT");

        return list;
    }
}
