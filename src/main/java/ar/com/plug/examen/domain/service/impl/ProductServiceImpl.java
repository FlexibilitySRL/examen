package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.enums.Result;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.exception.ProductParamException;
import ar.com.plug.examen.domain.model.LogTransation;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.LogTransationRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
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

import static ar.com.plug.examen.domain.constants.ErrorConstants.*;


@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
    private static final String CATEGORY = "category";
    private static final String PRICE = "price";
    private static final String STOCK = "stock";
    private static final String DESCRIPTION_PRODUCT = "description product";
    private static final String ACTION_SAVE = "guardar producto";
    private static final String ACTION_EMPTY_DATA = "editar producto o eliminar producto";
    private static final String VALIDATE_DATA = "validación datos";
    private static final String ACTION_DELETE = "eliminar producto";
    private static final String ACTION_EDIT = "editar producto";
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LogTransationRepository logTransationRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;


    @Override
    public void createProduct(ProductDTO productDTO) {
        try {
            validateInputData(productDTO);
            existsProductDescription(productDTO.getCategory(), productDTO.getDescriptionProduct());
            Product productToSave = new Product();
            productToSave.setDescriptionProduct(productDTO.getDescriptionProduct());
            productToSave.setCategory(productDTO.getCategory());
            productToSave.setPrice(productDTO.getPrice());
            productToSave.setStock(productDTO.getStock());
            Product productSave = productRepository.saveAndFlush(productToSave);

            StringBuilder description = new StringBuilder();
            description.append(SAVE_SUCCESS);
            description.append(" ");
            description.append(productSave.getIdProduct());
            createLog(ACTION_SAVE, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_SAVE, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Long idProduct) {
        try {
            existsProduct(idProduct);
            existsRelationProduct(idProduct);
            productRepository.deleteById(idProduct);
            StringBuilder description = new StringBuilder();
            description.append(DELETE_SUCCESS);
            description.append(" ");
            description.append(idProduct);
            createLog(ACTION_DELETE, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_DELETE, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void editProduct(Long idProduct, ProductDTO productDTO) {
        try {
            validateInputData(productDTO);
            Optional<Product> productResult = existsProduct(idProduct);
            productResult.get().setDescriptionProduct(productDTO.getDescriptionProduct());
            productResult.get().setCategory(productDTO.getCategory());
            productResult.get().setPrice(productDTO.getPrice());
            productResult.get().setStock(productDTO.getStock());
            productResult.get().setUpdateDate(LocalDateTime.now());
            productRepository.saveAndFlush(productResult.get());
            StringBuilder description = new StringBuilder();
            description.append(EDIT_SUCCESS);
            description.append(" ");
            description.append(idProduct);
            createLog(ACTION_EDIT, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_EDIT, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
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
            createLog(VALIDATE_DATA, Result.ERROR, ERROR_DATA_EMPTY);
            throw new ProductParamException(INVALID_PRODUCT_FIELD, param);
        }
    }

    private Optional<Product> existsProduct(long idProduct) {
        Optional<Product> productResult = productRepository.findById(idProduct);
        if (!productResult.isPresent()) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EMPTY_ID);
            description.append(" ");
            description.append(idProduct);
            createLog(ACTION_EMPTY_DATA, Result.ERROR, description.toString());
            throw new ProductNotFoundException();
        }
        return productResult;
    }

    private void existsProductDescription(String category, String descriptionProduct) {
        if (productRepository.findProductByCategoryAndDescriptionProduct(category, descriptionProduct) != null) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EXIST_PRODUCT);
            description.append(" ");
            description.append(category);
            description.append(" ");
            description.append(descriptionProduct);
            createLog(ACTION_SAVE, Result.ERROR, description.toString());
            throw new ProductNotFoundException();
        }
    }

    private List<Purchase> existsRelationProduct(long idProduct) {
        List<Purchase> purchaseResult = purchaseRepository.findProductByCustomerIdCustomerOrProductIdProductOrSellerIdSeller(null,idProduct, null);
        if (!purchaseResult.isEmpty()) {
           StringBuilder description = new StringBuilder();
           description.append(ERROR_ID);
           description.append(" ");
           description.append(idProduct);
           createLog(ACTION_DELETE, Result.ERROR, description.toString());
           throw new ProductNotFoundException();
        }
        return purchaseResult;
    }


    private void createLog(String action, Result result, String description) {
        LogTransation logTransation = LogTransation.builder()
                .module(action)
                .Result(result)
                .description(description).build();
        logTransationRepository.save(logTransation);
    }

}
