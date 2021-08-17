package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.objects.JsonResponseTransaction;



public interface ProductService {

    JsonResponseTransaction addProduct(ProductModel productModel);
    JsonResponseTransaction deleteProduct(Long id);
    JsonResponseTransaction updateProduct(ProductModel productModel);

}
