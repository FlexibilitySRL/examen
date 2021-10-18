package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDTO save(ProductDTO productDto);
    ProductDTO update(ProductDTO productDto);
    void delete(long productId);

    Optional<ProductDTO> getById(long productId);
    Optional<List<ProductDTO>>  findByStokGreatherThan(int stock);
    Optional<List<ProductDTO>>  findByPriceIsLessthan(double price);
    List<ProductDTO> getAll();

}
