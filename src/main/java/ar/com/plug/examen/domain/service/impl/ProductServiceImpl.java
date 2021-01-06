package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.common.impl.RequestTool;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductUseCase;
import ar.com.plug.examen.infrastructure.exception.ProductDuplicateException;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.function.BiFunction;

@Service
@Log4j2
public class ProductServiceImpl implements ProductUseCase {

    @Autowired
    private ProductRepository repository;

    /**
     * This method allows save Product
     * @param @{@link ProductApi} product
     * @return @{@link ProductApi}
     * @throws ProductDuplicateException
     */
    @Override
    public ProductApi save(ProductApi product) throws ProductDuplicateException {
        log.info("save Product begins");
        if(validateProduct().apply(product.getBrand(),product.getName())) {
            log.info("Product Duplicated");
            throw new ProductDuplicateException("Product Duplicated");
        }
        return RequestTool.parseProduct().apply(repository.save(RequestTool.swapProduct(product)));
    }

    /**
     * This method allows update Product
     * @param @{@link ProductApi} product
     * @param id
     * @return @{@link ProductApi}
     * @throws ResourceNotFoundException
     * @throws ProductDuplicateException
     */
    @Override
    public ProductApi update(ProductApi product, Long id) throws ResourceNotFoundException, ProductDuplicateException {
        log.info("Update Product begins");
        Product productEntity = findProduct(id);
        if (validateProductUpdate().apply(product,productEntity)) {
            log.info("Product Duplicated");
            throw new ProductDuplicateException("Product Duplicated");
        }
        return RequestTool.parseProduct().apply(repository.save(RequestTool.swapProduct(product,id)));
    }

    /**
     *  This method allows update Product
     * @param id
     * @return Boolean
     * @throws ResourceNotFoundException
     */
    @Override
    public Boolean delete(Long id) throws ResourceNotFoundException {
        log.info("Delete Product begins");
        Product productEntity = findProduct(id);
        return repository.deleteProduct(productEntity.getId()) == 1 ? true : false;
    }



    private BiFunction<String,String,Boolean> validateProduct() {
        return (brand, name) -> {
            log.info("find duplicated Product");
            return repository.findProductByBrandAndAndName(brand,name) >= 1;
        };
    }


    private BiFunction<ProductApi,Product,Boolean> validateProductUpdate() {
        return (productApi, product) -> {
            log.info("find duplicated Product");
            if (product.getBrand().equals(productApi.getBrand())
                    && product.getName().equals(productApi.getName()))
                return false;
            return validateProduct().apply(productApi.getBrand(),productApi.getName());
        };
    }

    private Product findProduct(Long id) throws ResourceNotFoundException {
        log.info("find Product");
        return repository.findProductById(id).orElseThrow(
                () ->  {
                    log.info("Product not found with Id " + id);
                    return new ResourceNotFoundException("Product not found with Id " + id);
                });
    }

}
