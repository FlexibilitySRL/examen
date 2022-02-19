package ar.com.plug.examen.domain.service;

import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.domain.model.Product;

public interface ProductService
{
	PageDto<Product> getActiveProductsPageable(int pageNumber, int pageSize);

	PageDto<Product> getAllProductsPageable(int pageNumber, int pageSize);

	Product getProductById(Long id);

	Product getProductBySku(String sku);

	Product saveProduct(ProductDto productDto) throws ValidationException;

	Product updateProduct(Long id, ProductDto productDto) throws ValidationException;

	Product inactivateProduct(Long id) throws ValidationException;

	Long deleteProduct(Long id) throws ValidationException;
}
