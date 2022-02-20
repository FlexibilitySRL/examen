package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.exception.ProductParamException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.util.LoggerExample;
import ar.com.plug.examen.domain.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.com.plug.examen.domain.constants.ErrorConstants.INVALID_PRODUCT_FIELD;


@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
    private static final String CATEGORY = "category";
    private static final String PRICE = "price";
    private static final String STOCK = "stock";
    private static final String DESCRIPTION_PRODUCT = "description product";
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDTO productDTO) {
        try {
            validateInputData(productDTO);
            Product productToSave = new Product();
            productToSave.setDescriptionProduct(productDTO.getDescriptionProduct());
            productToSave.setCategory(productDTO.getCategory());
            productToSave.setPrice(productDTO.getPrice());
            productToSave.setStock(productDTO.getStock());
            productRepository.save(productToSave);
        } catch (ExceptionInInitializerError ex) {
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        } finally {

        }
    }

    @Override
    public void deleteProduct(Long idProduct) {
        try {
            existsProduct(idProduct);
            productRepository.deleteById(idProduct);
        } catch (ExceptionInInitializerError ex) {
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        } finally {

        }
    }

    @Override
    public void editProduct(Long idProduct, ProductDTO productDTO) {
        try {
            Optional<Product> productResult = existsProduct(idProduct);
            productResult.get().setDescriptionProduct(productDTO.getDescriptionProduct());
            productResult.get().setCategory(productDTO.getCategory());
            productResult.get().setPrice(productDTO.getPrice());
            productResult.get().setStock(productDTO.getStock());
            productResult.get().setUpdateDate(LocalDateTime.now());
            productRepository.save(productResult.get());
        } catch (ExceptionInInitializerError ex) {
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        } finally {

        }
    }

    private void validateInputData(ProductDTO productDTO) {
        List<String> param = new ArrayList<>();

        if (Util.isBlank(productDTO.getCategory())) {
            param.add(CATEGORY);
        }
        if (null == productDTO.getPrice()) {
            param.add(PRICE);
        }
        if (productDTO.getStock() == null) {
            param.add(STOCK);
        }
        if (Util.isBlank(productDTO.getDescriptionProduct())) {
            param.add(DESCRIPTION_PRODUCT);
        }
        if (!param.isEmpty()) {
            throw new ProductParamException(INVALID_PRODUCT_FIELD, param);
        }
    }

    private Optional<Product> existsProduct(long idProduct) {
        Optional<Product> productResult = productRepository.findById(idProduct);
        if (!productResult.isPresent()) {
            throw new ProductNotFoundException();
        }
        return productResult;
    }

}
