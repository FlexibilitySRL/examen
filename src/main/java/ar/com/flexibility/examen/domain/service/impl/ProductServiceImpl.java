package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.mappers.EntityApiMapper;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repositories.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;
    private EntityApiMapper entityApiMapper;

    @Override
    public ProductApi create(ProductApi productApi) {
        logger.debug("Validating required data...");
        if (StringUtils.isBlank(productApi.getName())) {
            logger.error("The name is required");
            throw new BadRequestException("Required data is missing: name");
        }
        Product product = entityApiMapper.destinationToSourceProductApi(productApi);
        productRepository.save(product);

        logger.info("Product created {}", product.getId());
        return entityApiMapper.sourceToDestinationProduct(product);
    }

    @Override
    public ProductApi retrieve(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id));

        return entityApiMapper.sourceToDestinationProduct(product);
    }

    @Override
    public List<ProductApi> list() {
        List<Product> products = productRepository.findAll();
        return entityApiMapper.sourceToDestinationProducts(products);
    }

    @Override
    public void remove(Long id) {
        if (!productRepository.exists(id)) {
            throw new NotFoundException("Product with id " + id);
        }
        productRepository.delete(id);
    }

    @Override
    public ProductApi update(Long id, ProductApi productApi) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id));

        product.setDescription(productApi.getDescription());
        return entityApiMapper.sourceToDestinationProduct(productRepository.save(product));
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setEntityApiMapper(EntityApiMapper entityApiMapper) {
        this.entityApiMapper = entityApiMapper;
    }
}
