package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;

import java.util.List;

/**
 *  Product Service contract. It exposes the methods that should be
 *  implemented by the more specific classes.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
public interface ProductService {

    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);

    List<Product> retrieveProducts();
    Product retrieveProductById(Long id);

}
