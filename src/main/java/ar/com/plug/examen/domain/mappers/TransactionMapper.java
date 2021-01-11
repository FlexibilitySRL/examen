package ar.com.plug.examen.domain.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.model.Transaction;

@Component
public class TransactionMapper implements Mapper<Transaction, TransactionApi>{

	@Override
	public TransactionApi getDto(Transaction entity) {
		
		TransactionApi dto = new TransactionApi();
		
		dto.setId(entity.getClientId());
		dto.setProductId(entity.getProductId());
		dto.setClientId(entity.getClientId());
		dto.setSellerId(entity.getSellerId());
		dto.setPrice(entity.getPrice());
		dto.setStatus(entity.getStatus());
		dto.setDate(entity.getDate());
		
		return dto;
	}

	@Override
	public Transaction fillEntity(Transaction entity, TransactionApi dto) {
		
		entity.setId(dto.getClientId());
		entity.setProductId(dto.getProductId());
		entity.setClientId(dto.getClientId());
		entity.setSellerId(dto.getSellerId());
		entity.setPrice(dto.getPrice());
		entity.setStatus(dto.getStatus());
		entity.setDate(dto.getDate());
		
		return entity;
	}

	@Override
	public List<TransactionApi> getDto(Collection<Transaction> entities) {
		
		List<TransactionApi> dto = new ArrayList<>();
		
		for(Transaction transaction : entities) {
			dto.add(getDto(transaction));
		}
		
		return dto;
	}

}
