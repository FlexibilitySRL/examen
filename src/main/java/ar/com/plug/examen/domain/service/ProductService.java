package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.entity.Product;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    ProductService
 * Description:             Interface for handling service layer of Product's Entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public interface ProductService
{
    List<ProductDTO> findAll();
    ProductDTO findById( Product product );
    ProductDTO register( Product buyer );
    ProductDTO update( Product buyer );
    void delete( Product buyer );
}
