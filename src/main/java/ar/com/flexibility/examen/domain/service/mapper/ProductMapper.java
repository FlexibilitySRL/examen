package ar.com.flexibility.examen.domain.service.mapper;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);

    @Mapping(target = "createdDate", ignore = true)
    Product toEntity(ProductDTO ProductDTO);
}
