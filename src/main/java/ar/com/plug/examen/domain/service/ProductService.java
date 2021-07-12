package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.ProductStockApi;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.model.Product;
import java.util.List;

public interface ProductService {

  /**
   * Get all products
   * @return List<ProductApi>
   */
  List<ProductApi> findAll();

  /**
   * Get product by id
   * @param id
   * @return ProductApi
   * @throws GenericNotFoundException
   */
  ProductApi findByIdChecked(Long id) throws GenericNotFoundException;

  /**
   * Save a product
   * @param productApi
   * @return ProductApi
   * @throws GenericBadRequestException
   */
  ProductApi save(ProductApi productApi) throws GenericBadRequestException;

  /**
   * Update a product
   * @param productApi
   * @return ProductApi
   * @throws GenericNotFoundException
   * @throws GenericBadRequestException
   */
  ProductApi update(ProductApi productApi) throws GenericNotFoundException, GenericBadRequestException;

  /**
   * Delete a product by id
   * @param id
   * @throws GenericNotFoundException
   */
  void delete(Long id) throws GenericNotFoundException;

  /**
   * Return all products in stock
   * @param lsProducts
   * @return List<Product>
   */
  List<Product> getProductsWithStock(List<ProductStockApi> lsProducts);

}
