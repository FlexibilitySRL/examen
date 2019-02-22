package ar.com.flexibility.examen.util;

import org.apache.commons.lang3.StringUtils;
import ar.com.flexibility.examen.domain.exception.ProductNameNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.ProductPriceNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.ProductStockNotAcceptedException;
import ar.com.flexibility.examen.domain.model.Product;

public class ValidationHelper {
	
	private ValidationHelper() {
		throw new IllegalStateException("Validacion class");
	}

	public static void validateProduct(Product product) throws ProductNameNotAcceptedException, ProductPriceNotAcceptedException, ProductStockNotAcceptedException {
		validateProductName(product.getName());
		validatePrice(product.getPrice());
		validateStock(product.getStock());
	}

	private static void validateStock(Integer stock) throws ProductStockNotAcceptedException {
		if( stock == null || stock <= 0) {
			throw new ProductStockNotAcceptedException();
		}
	}

	private static void validatePrice(Double price) throws ProductPriceNotAcceptedException {
		if( price == null || price <= 0) {
			throw new ProductPriceNotAcceptedException();
		}
		
	}

	private static void validateProductName(String name) throws ProductNameNotAcceptedException {
		if(StringUtils.isEmpty(name) || !(name instanceof String)) {
			throw new ProductNameNotAcceptedException();
		}
		
	}
}
