package ar.com.plug.examen.domain.repository.impl;

import ar.com.plug.examen.domain.crud.ProductCrudRepository;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Override
    public Product save(Product product) {
        return productCrudRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productCrudRepository.save(product);
    }

    @Override
    public void delete(long productId) {
       productCrudRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> getById(long productId) {
        return productCrudRepository.findById(productId);
    }

    @Override
    public Optional<List<Product>> findByStokGreatherThan(int stock) {
        return productCrudRepository.findByStockIsGreaterThan(stock);
    }

    @Override
    public Optional<List<Product>>  findByPriceIsLessthan(double price) {
        return productCrudRepository.findByPriceIsLessThan(price);
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) productCrudRepository.findAll();
    }
}
