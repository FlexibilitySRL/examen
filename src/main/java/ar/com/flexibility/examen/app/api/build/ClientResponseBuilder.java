package ar.com.flexibility.examen.app.api.build;

import java.util.ArrayList;
import java.util.List;

import ar.com.flexibility.examen.app.api.response.ClientApiResponse;
import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.domain.model.Client;

public class ClientResponseBuilder {

	public static ClientApiResponse mergeResponse(Client entity) {
		ClientApiResponse response = new ClientApiResponse();
		response.setIdentifier(entity.getIdentifier());
		response.setName(entity.getName());
		response.setSurname(entity.getSurname());
		response.setPurchases(mergePurchases(entity));

		return response;
	}

	private static List<SaleApiResponse> mergePurchases(Client entity) {
		List<SaleApiResponse> data = new ArrayList<>();

		entity.getPurchases().stream().forEach(e -> data.add(SaleResponseBuilder.mergeResponse(e)));

		return data;
	}
}
