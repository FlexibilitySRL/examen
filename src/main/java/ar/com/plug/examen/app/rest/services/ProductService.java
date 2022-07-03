package ar.com.plug.examen.app.rest.services;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.app.rest.model.Product;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProductService
{
    PageDto<Product> getAllProductsPageable(int pageNumber, int pageSize);

    Product getProductById(Long id);

    Product saveProduct(ProductDto productDto) throws ValidationException;

    List<Product> bulkSaveProduct(List<ProductDto> productDto) throws ValidationException;

    Boolean inactivateProduct(Long id) throws ValidationException;

}