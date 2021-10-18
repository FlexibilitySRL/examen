package ar.com.plug.examen.domain.mapper;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDto(Product product);
    List<ProductDTO> toListProductDto(List<Product> products);

    @InheritInverseConfiguration
    Product toProduct(ProductDTO productDTO);

}
