package ar.com.flexibility.examen.domain.service.mapper;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Product and its Api ProductApi.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper<ProductApi, Product> {



    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
