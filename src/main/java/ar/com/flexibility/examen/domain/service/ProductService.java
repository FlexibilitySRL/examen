package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.ProductApi;

import java.util.List;

public interface ProductService {

    ProductApi create(ProductApi productApi);

    ProductApi retrieve(Long id);

    List<ProductApi> list();

    void remove(Long id);

    ProductApi update(Long id, ProductApi productApi);
}
