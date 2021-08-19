package ar.com.plug.examen.domain.converter;


import ar.com.plug.examen.domain.dto.Product;
import ar.com.plug.examen.domain.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends Converter<Product, ProductModel> {

    public ProductConverter() {
        super(ProductConverter::convertToEntity, ProductConverter::convertToModel);
    }

    private static Product convertToModel(ProductModel productModel) {
        return new Product(productModel.getId(), productModel.getName(), productModel.getDescription(), productModel.getUnitCost(), productModel.getSalePrice(), productModel.getQuantity(), productModel.getIdStatus());
    }

    private static ProductModel convertToEntity(Product dto) {
        return new ProductModel(dto.getId(), dto.getName(), dto.getDescription(), dto.getUnitCost(), dto.getSalePrice(), dto.getQuantity(), dto.getIdStatus());
    }

}