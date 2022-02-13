package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product save(Product product);

    Product findById(long id);

    void deleteById(long id);
}
