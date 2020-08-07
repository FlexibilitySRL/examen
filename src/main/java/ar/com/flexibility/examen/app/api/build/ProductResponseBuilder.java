package ar.com.flexibility.examen.app.api.build;

import java.util.ArrayList;
import java.util.List;

import ar.com.flexibility.examen.app.api.response.ProductApiResponse;
import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.domain.model.Product;

public class ProductResponseBuilder {
	
	private ProductResponseBuilder () {}

	public static ProductApiResponse mergeResponse (Product entity) {		
		ProductApiResponse response = new ProductApiResponse();
		response.setAmount(entity.getAmount());
		response.setCode(entity.getCode());
		response.setName(entity.getName());
		response.setPrice(entity.getPrice());
		response.setSales(mergeSales(entity));
		
		return response;
	}
	
	private static List<SaleApiResponse> mergeSales (Product entity) {
		List<SaleApiResponse> data = new ArrayList<>();

		entity.getSales().stream().forEach(e -> data.add(SaleResponseBuilder.mergeResponse(e)));

		return data;
	}
}
