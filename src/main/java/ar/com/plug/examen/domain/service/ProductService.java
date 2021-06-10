package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.ProductModel;
import java.util.List;

/**
 *
 * @author AGB
 */
public interface ProductService {

   ProductModel saveProduct(ProductModel product);
   
   List<ProductModel> saveProducts(List<ProductModel> products);
   
   List<ProductModel> getProducts();
   
   ProductModel getProductById(int id);
   
   ProductModel getProductByName(String name);
   
   String deleteProduct(int id);
   
   ProductModel updateProduct(ProductModel product);
   
   
}
