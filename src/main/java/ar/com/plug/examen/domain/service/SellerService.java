package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.entity.Seller;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    SellerService
 * Description:             Interface for handling service layer of Seller's Entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public interface SellerService
{
    List<SellerDTO> findAll();
    SellerDTO findById( Seller seller );
    SellerDTO register( Seller seller );
    SellerDTO update( Seller seller );
    void delete( Seller seller );
}