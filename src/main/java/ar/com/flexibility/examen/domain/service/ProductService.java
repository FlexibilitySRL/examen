/**
 * 
 */
package ar.com.flexibility.examen.domain.service;


import java.util.List;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;

/**
 * @author rosalizaracho
 *
 */

public interface ProductService {
  
	ProductApi saveOrUpdate(Product product);

	List<ProductApi> findAll();

    List<ProductApi>findBySeller(Seller seller);
}
