/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.ProductStockApi;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.ProductMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.validators.Validator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private Validator validator;

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ProductService#findAll()
     */
    @Override
    public List<ProductApi> findAll() {
        return this.productMapper
                .productsToListProductApi(this.productRepository.findAll());
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * ar.com.plug.examen.domain.service.ProductService#findByIdChecked(Long) ()
     */
    @Override
    public ProductApi findById(Long id) throws GenericNotFoundException {
        return this.productMapper.productToProductApi(
                this.productRepository.findById(id)
                        .orElseThrow(() -> new GenericNotFoundException("Product not found")));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ProductService#save(ProductApi) ()
     */
    @Override
    @Transactional
    public ProductApi save(ProductApi productApi) throws GenericBadRequestException {
        this.validator.validateProduct(productApi, Boolean.FALSE);
        return this.productMapper.productToProductApi(
                this.productRepository.save(this.productMapper.productApiToProduct(productApi)));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ProductService#update(ProductApi)
     * ()
     */
    @Override
    @Transactional
    public ProductApi update(ProductApi productApi)
            throws GenericNotFoundException, GenericBadRequestException {
        this.validator.validateProduct(productApi, Boolean.TRUE);
        this.productRepository.findById(productApi.getId())
                .orElseThrow(() -> new GenericNotFoundException("Product not found"));
        return this.productMapper.productToProductApi(
                this.productRepository.save(this.productMapper.productApiToProduct(productApi)));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.ProductService#delete(Long) ()
     */
    @Override
    @Transactional
    public void delete(Long id) throws GenericNotFoundException {
        this.productRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Product not found"));
        this.productRepository.deleteById(id);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * ar.com.plug.examen.domain.service.ProductService#getProductsWithStock(List<ProductStockApi>)
     * ()
     */
    @Override
    public List<Product> getProductsWithStock(List<ProductStockApi> lsProducts) {
        List<Product> products = new ArrayList<>();
        for (ProductStockApi productStockApi : lsProducts) {
            Product product = this.productRepository
                    .findByIdAndStockGreaterThanEqual(productStockApi.getIdProduct(),
                            productStockApi.getQuantity()).orElseThrow(() -> new GenericBadRequestException(
                    "Product with id:" + productStockApi.getIdProduct() + " not found or out of stock"));
            products.add(product);
        }
        return products;
    }
}
