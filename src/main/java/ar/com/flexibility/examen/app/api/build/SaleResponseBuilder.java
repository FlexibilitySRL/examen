package ar.com.flexibility.examen.app.api.build;

import java.util.Objects;

import ar.com.flexibility.examen.app.api.response.SaleApiResponse;
import ar.com.flexibility.examen.domain.model.Sale;

public class SaleResponseBuilder {
	
	private SaleResponseBuilder () {}

	public static SaleApiResponse mergeResponse (Sale entity) {		
		SaleApiResponse response = new SaleApiResponse();
		response.setClientIdentifier(entity.getClient().getIdentifier());
		response.setCode(entity.getCode());
		response.setDate(entity.getDate().toString());
		response.setProductAmount(entity.getAmount());
		response.setProductCode(entity.getProduct().getCode());
		response.setSellerIdentifier(entity.getSeller().getIdentifier());
		response.setStatus(entity.getStatus().name());
		response.setValue(entity.getValue());
		
		if (Objects.nonNull(entity.getDateApproved())) {
			response.setDateApproved(entity.getDateApproved().toString());
		}
		
		return response;
	}
	
}
