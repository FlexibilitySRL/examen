package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public  Product deleteProduct(Long id);
    public Product updateStock(Long id, Double quantity);
}
