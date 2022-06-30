package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.execptions.ChallengeException;
import ar.com.plug.examen.domain.execptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductApi> findById(Long productId);


    ProductApi getProductById(Long productId) throws ChallengeException;

    ProductApi createProduct(ProductApi productApi);

    List<ProductApi> listAllProducts();

    void removeProduct(Long productId) throws NotFoundException;

    ProductApi updateProduct(Long productId, ProductApi productApi) throws NotFoundException;
}
