package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);
    Product update(Product product);
    void delete(long productId);

    Optional<Product> getById(long productId);
    Optional<List<Product>>  findByStokGreatherThan(int stock);
    Optional<List<Product>>  findByPriceIsLessthan(double price);
    List<Product> getAll();

}
