package ar.com.plug.examen.app.mapper;

import ar.com.plug.examen.app.DTO.ProductDTO;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO asDTO(ProductApi source) {
        return new ProductDTO(null, source.getName(), source.getDescription(), source.getPrice());
    }

    public ProductDTO asDTO(Product source) {
        return new ProductDTO(source.getId(), source.getName(), source.getDescription(), source.getPrice());

    }

}