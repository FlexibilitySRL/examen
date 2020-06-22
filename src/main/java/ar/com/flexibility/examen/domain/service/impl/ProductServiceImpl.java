package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.mappers.ApiMapper;
import ar.com.flexibility.examen.domain.mappers.EntityMapper;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ApiMapper apiMapper;

    @Override
    public ProductApi create(ProductApi productApi) throws BadRequestException {
        log.info("Saving to product repository product: " + productApi);
        Product product = productRepository.save(apiMapper.toProduct(productApi));
        return entityMapper.toProductApi(product);
    }

    @Override
    public ProductApi get(Long id) throws NotFoundException {
        log.info("Retrieving product from repository with id: " + id);
        Product product = productRepository.findOne(id);
        if (product == null)
            throw new NotFoundException("Product with id " + id);
        return entityMapper.toProductApi(product);
    }

    @Override
    public List<ProductApi> all() {
        log.info("Retrieving all products from product repository.");
        List<Product> products = productRepository.findAll();
        return entityMapper.toProductsApi(products);
    }

    @Override
    public ProductApi update(Long id, ProductApi productApi) throws BadRequestException, NotFoundException {
        log.info("Updating product with id " + id + " in product repository with product: " + productApi);
        Product product = productRepository.findOne(id);
        if (product == null)
            throw new NotFoundException("Product with id " + id);
        product.setName(productApi.getName());
        product.setDescription(productApi.getDescription());
        Product newProduct = productRepository.save(product);
        return entityMapper.toProductApi(newProduct);
    }

    @Override
    public void remove(Long id) throws NotFoundException {
        log.info("Deleting id " + id + " from product repository");
        if (!productRepository.exists(id)) {
            throw new NotFoundException("Client id " + id + "doesn't exist");
        }
        productRepository.delete(id);
    }
}
