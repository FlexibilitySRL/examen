package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(ProductDTO productDTO);
    void delete(long productId);

    Optional<ProductDTO> getById(long productId);
    Optional<List<ProductDTO>> findByStokGreatherThan(int stock);
    Optional<List<ProductDTO>>  findByPriceIsLessthan(double price);
    List<ProductDTO> getAll();

}
