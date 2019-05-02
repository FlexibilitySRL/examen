package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findOne(Long id);
    Boolean exists(Long id);
    Product add(Product product);
    Product update(Product product);
    Boolean delete(Long id);

}
