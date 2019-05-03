package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.exception.GenericProductException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public Boolean exists(Long id){
        return productRepository.exists(id);
    }

    @Override
    public void deleteAll(){
        productRepository.deleteAll();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(Long id) throws NotFoundException {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new NotFoundException(String.format(PRODUCT_ID_NOT_EXIST, id));
        }
        return product;
    }

    @Override
    public Product add(Product product) throws GenericProductException {
        Product productAdded = productRepository.saveAndFlush(product);
        if (productAdded == null) {
            throw new GenericProductException(PRODUCT_ADDED_FAILED);
        }
        return productAdded;
    }

    @Override
    public Product update(Product product) throws NotFoundException, GenericProductException {

        Product productToPersist = productRepository.findOne(product.getId());

        if (productToPersist == null) {
            throw new NotFoundException(String.format(PRODUCT_ID_NOT_EXIST, product.getId()));

        } else if (productToPersist.equals(product)){
            throw new GenericProductException(PRODUCT_TO_UPDATE_WITHOUT_CHANGES);
        }

        productToPersist.setDescription(product.getDescription());
        productToPersist.setPrice(product.getPrice());

        return productRepository.saveAndFlush(productToPersist);
    }

    @Override
    public void delete(Long id) throws NotFoundException, GenericProductException {

        if (!exists(id)) {
            throw new NotFoundException(String.format(PRODUCT_ID_NOT_EXIST, id));
        }

        productRepository.delete(id);

        if (exists(id)){
            throw new GenericProductException(PRODUCT_DELETE_FAILED);
        }

    }

}
