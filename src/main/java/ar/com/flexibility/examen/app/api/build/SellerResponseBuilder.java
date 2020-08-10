package ar.com.flexibility.examen.app.api.build;

import java.util.ArrayList;
import java.util.List;

import ar.com.flexibility.examen.app.api.response.SellerApiResponse;
import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.domain.model.Seller;

public class SellerResponseBuilder {
	
	private SellerResponseBuilder () {}

	public static SellerApiResponse mergeResponse(Seller entity) {
		SellerApiResponse response = new SellerApiResponse();
		response.setIdentifier(entity.getIdentifier());
		response.setName(entity.getName());
		response.setSurname(entity.getSurname());
		response.setSales(mergeSales(entity));

		return response;
	}

	private static List<SaleApiResponse> mergeSales(Seller entity) {
		List<SaleApiResponse> data = new ArrayList<>();

		entity.getSales().stream().forEach(e -> data.add(SaleResponseBuilder.mergeResponse(e)));

		return data;
	}
}
