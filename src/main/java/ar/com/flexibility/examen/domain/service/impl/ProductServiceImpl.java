package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.mappers.AutoMapperApiEntity;
import ar.com.flexibility.examen.domain.mappers.AutoMapperEntityApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repositories.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private AutoMapperEntityApi autoMapperEntityApi;
    private AutoMapperApiEntity autoMapperApiEntity;
	private Log log = LogFactory.getLog(ProductServiceImpl.class);
	
	
	@Override
	public ProductApi create(ProductApi productApi) {
		Product product = autoMapperApiEntity.sourceToDestinationProductApi(productApi);
		productRepository.save(product);
        log.debug("Product created");
        return autoMapperEntityApi.sourceToDestinationProduct(product);
	}

	@Override
	public ProductApi get(Long id) {
		Product product = null;
		try {
			product = productRepository.findById(id)
			        .orElseThrow(() -> new NotFoundException());
	        log.debug("Get Product");
		} catch (NotFoundException e) {
	        log.debug("Producte not found");
		}
        return autoMapperEntityApi.sourceToDestinationProduct(product);
	}

	@Override
	public List<ProductApi> getProducts() {
		List<Product> products = productRepository.findAll();
        return autoMapperEntityApi.sourceToDestinationProducts(products);
	}

	@Override
	public void delete(Long id) {
        productRepository.delete(id);
	}

	@Override
	public ProductApi update(Long id, ProductApi productApi) {
		Product product = null;
		try {
			product = productRepository.findById(id)
			        .orElseThrow(() -> new NotFoundException());
	        product.setName(productApi.getName());
	        product.setDescription(productApi.getDescription());
	        productRepository.saveAndFlush(product);
	        log.debug("Update Product");
		} catch (NotFoundException e) {
	        log.debug("Producte not found");
		}
        return autoMapperEntityApi.sourceToDestinationProduct(productRepository.save(product));
	}

}
