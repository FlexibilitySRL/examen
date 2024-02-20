package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exception.ProductException;
import ar.com.plug.examen.domain.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> viewAllProduct() throws ProductException;

    public Product addProduct(Product product) throws ProductException;

    public Product updateProduct(Product product) throws ProductException;

    public Product viewProduct(Integer productId) throws ProductException;

    public Product removeProduct(Integer productId) throws ProductException;
}
