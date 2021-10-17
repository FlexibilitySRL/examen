package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.IProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(long productId) {

       productRepository.delete(productId);
    }

    @Override
    public Optional<Product> getById(long productId) {
        return productRepository.getById(productId);
    }

    @Override
    public Optional<List<Product>> findByStokGreatherThan(int stock) {
        return productRepository.findByStokGreatherThan(stock);
    }

    @Override
    public Optional<List<Product>>  findByPriceIsLessthan(double price) {
        return productRepository.findByPriceIsLessthan(price);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }
}
