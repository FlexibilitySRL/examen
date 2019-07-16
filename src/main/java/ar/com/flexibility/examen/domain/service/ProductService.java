package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.service.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<ProductDTO> findOne(Long id);

    List<ProductDTO> findAll();

    ProductDTO save(ProductDTO productDTO);

    void delete(Long id);

}
