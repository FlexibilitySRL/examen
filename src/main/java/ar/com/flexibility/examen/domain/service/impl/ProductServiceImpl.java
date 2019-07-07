package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product createProduct (Product product) throws ProductExistsException {
            return productRepository.save(product);
    }


    @Override
    public void updateProduct(Product product) throws ProductNotFoundException {

        if (!productRepository.exists (product.getId ()))
            throw new ProductNotFoundException();

        productRepository.save(product);
    }


    @Override
    public Product getProductById (Long id) throws ProductNotFoundException {
        Product product = productRepository.findOne (id);

        if (product == null)
            throw new ProductNotFoundException();

        return product;
    }


    @Override
    public void deleteProduct (Long id) throws ProductNotFoundException {
        try {
            productRepository.delete(id);
        }catch (EmptyResultDataAccessException ex) {
            throw new ProductNotFoundException();
        }
    }

}
