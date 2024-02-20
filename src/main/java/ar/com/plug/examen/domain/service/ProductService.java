package ar.com.plug.examen.domain.service;

import java.util.Set;

import ar.com.plug.examen.domain.Product;

public interface ProductService {
    Product findById(String id);

    Set<Product> findAllByFilter(Product product);

    Product create(Product client);

    Product upDate(Product client);

    void remove(String id);
}
