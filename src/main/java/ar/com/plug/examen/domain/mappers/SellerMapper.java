package ar.com.plug.examen.domain.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.model.Seller;

@Component
public class SellerMapper implements Mapper<Seller, SellerApi>{

	@Override
	public SellerApi getDto(Seller entity) {
		
		SellerApi dto = new SellerApi();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		
		return dto;
	}

	@Override
	public Seller fillEntity(Seller entity, SellerApi dto) {

		entity.setId(dto.getId());
		entity.setName(dto.getName());
		
		return entity;
	}

	@Override
	public List<SellerApi> getDto(Collection<Seller> entities) {
		
		List<SellerApi> dto = new ArrayList<>();
		
		for(Seller seller : entities) {
			dto.add(getDto(seller));
		}
		
		return dto;
	}

}
