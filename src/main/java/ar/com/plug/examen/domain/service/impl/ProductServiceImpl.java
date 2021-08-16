package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.converter.ProductConverter;
import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.dto.Product;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import ar.com.plug.examen.objects.StatusTransaction;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Log log = LogFactory.getLog(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public JsonResponseTransaction addProduct(ProductModel productModel) {
        log.info("Call: product " + " addProductModel");
        Product product = productRepository.save(productConverter.convertFromEntity(productModel));
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        jsonResponseTransaction = validateStatus(productModel, jsonResponseTransaction);
        jsonResponseTransaction.setProductModel(productConverter.convertFromModel(product));
        jsonResponseTransaction.setStatusTransaction(StatusTransaction.fromId(productModel.getIdStatus()));
        return jsonResponseTransaction;
    }

    @Override
    public JsonResponseTransaction deleteProduct(Integer id) {
        log.info("Call: product " + " deleteProduct " + id);
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        productRepository.deleteById(id);
        jsonResponseTransaction.setResponseMessage("Product: "+id + " eliminated of system");
        return jsonResponseTransaction;
    }

    @Override
    public JsonResponseTransaction updateProduct(ProductModel productModel) {
        log.info("Call: product " + " updateProductModel " + "productModel before: " + productConverter.convertFromModel(productRepository.findById(productModel.getId()))
                + System.lineSeparator() + " productModel after: " + productModel);
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        jsonResponseTransaction = validateStatus(productModel, jsonResponseTransaction);
        Product product = productRepository.save(productConverter.convertFromEntity(productModel));
        jsonResponseTransaction.setProductModel(productConverter.convertFromModel(product));
        jsonResponseTransaction.setStatusTransaction(StatusTransaction.fromId(productModel.getIdStatus()));
        return jsonResponseTransaction;
    }

    private JsonResponseTransaction validateStatus(ProductModel productModel, JsonResponseTransaction jsonResponseTransaction) {
        if (Objects.equals(productModel.getIdStatus(), StatusTransaction.INACTIVE.getId())) {
            jsonResponseTransaction.setResponseMessage("Product add with successfully but status is inactive");
        }
        if (Objects.equals(productModel.getIdStatus(), StatusTransaction.ACTIVE.getId())) {
            jsonResponseTransaction.setResponseMessage("Product add with successfully");
        }
        return jsonResponseTransaction;
    }

    @Override
    public ProductModel findProductModelById(Integer id) {
        return null;
    }
}
