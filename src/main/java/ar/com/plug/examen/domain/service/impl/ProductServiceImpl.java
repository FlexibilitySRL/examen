package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.repository.ProductRepository;
import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product save(Product aProduct) {
        return this.productRepository.save(aProduct);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> anOptionalProduct = this.productRepository.findById(id);
        if (! anOptionalProduct.isPresent()){
            //throw new ProductDoesNotExistException("The product does not exist");
            return null;
        }
        return anOptionalProduct.get();
    }

    @Override
    public void delete(Product aProduct) {
        this.productRepository.delete(aProduct);
    }

    @Override
    public Product updateProduct(Product aProduct) {
        Product product = this.findById(aProduct.getId());
        return this.save(product);
    }
}
