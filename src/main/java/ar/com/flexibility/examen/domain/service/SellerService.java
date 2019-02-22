/**
 * 
 */
package ar.com.flexibility.examen.domain.service;


import java.util.List;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exception.ProductNameNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.exception.ProductPriceNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.ProductStockNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.SellerNotFoundException;

/**
 * @author rosalizaracho
 *
 */

public interface SellerService {

    List<ProductApi>findBySeller(Long idSeller) throws SellerNotFoundException;

	void createNewProductToSeller(ProductApi productApi) throws SellerNotFoundException, ProductNameNotAcceptedException, ProductPriceNotAcceptedException, ProductStockNotAcceptedException;

	void updateProductOfSeller(ProductApi productApi) throws SellerNotFoundException, ProductNameNotAcceptedException, ProductPriceNotAcceptedException, ProductStockNotAcceptedException, ProductNotFoundException;
	
}
