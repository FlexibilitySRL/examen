package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.BuyerDTO;
import ar.com.plug.examen.domain.entity.Buyer;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    BuyerService
 * Description:             Interface for handling service layer of Buyer's Entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public interface BuyerService
{
    List<BuyerDTO> findAll();
    BuyerDTO findById( Buyer buyer );
    BuyerDTO register( Buyer buyer );
    BuyerDTO update( Buyer buyer );
    void delete( Buyer buyer );
}
