package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.repository.ProductRepository;
import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository repository){
        productRepository = repository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product aProduct) {
        return this.productRepository.save(aProduct);
    }

    @Override
    public Product findById(Long id) throws ProductDoesNotExistException {
        Product aProduct = this.productRepository.findById(id)
                .orElseThrow(()-> new ProductDoesNotExistException("The product with id: " + id.toString() + " does not exist."));
        return aProduct;
    }

    @Override
    public void deleteProduct(Long id) throws ProductDoesNotExistException {
        Product aProduct = this.findById(id);
        this.productRepository.delete(aProduct);
    }

    @Override
    public Product updateProduct(Product aProduct) throws ProductDoesNotExistException {
        Product product = this.findById(aProduct.getId());
        if(product.getId()== null || product.getId()<0 ){
            throw new ProductDoesNotExistException("The product with id: " + aProduct.getId().toString() + " does not exist.");
        }

        return this.saveProduct(aProduct);
    }
}
