package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.objects.JsonResponseTransaction;

import java.util.List;


public interface ProductService {

    JsonResponseTransaction addProduct(ProductModel productModel);
    JsonResponseTransaction deleteProduct(Integer id);
    JsonResponseTransaction updateProduct(ProductModel productModel);
    ProductModel findProductModelById(Integer id);

}
