package ar.com.plug.examen.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.dao.entities.Transaction;
import ar.com.plug.examen.domain.model.Status;
import ar.com.plug.examen.dto.TransactionDto;

@Component
public class TransactionMapper {

	public TransactionDto entityToDto(Transaction dao) {
		TransactionDto dto = new TransactionDto();
		dto.setClient(dao.getClient());
		dto.setProducts(dao.getProducts());
		dto.setTotalValue(dao.getTotalValue());
		if(dao.getStatus() == Status.CANCELLED) {
			dto.setStatus("CANCELLED");
		}
		else if(dao.getStatus() == Status.COMPLETED) {
			dto.setStatus("COMPLETED");			
		}
		else if(dao.getStatus() == Status.REJECTED) {
			dto.setStatus("REJECTED");			
		}
		return dto;
	}
	
	public Transaction dtoToEntity(TransactionDto dto) {
		Transaction dao = new Transaction();
		dao.setClient(dto.getClient());
		dao.setProducts(dto.getProducts());
		dao.setTotalValue(dto.getTotalValue());
		if(dto.getStatus() == "CANCELLED") {
			dao.setStatus(Status.CANCELLED);
		}
		else if(dto.getStatus() == "COMPLETED") {
			dao.setStatus(Status.COMPLETED);			
		}
		else if(dto.getStatus() == "REJECTED") {
			dao.setStatus(Status.REJECTED);			
		}
		return dao;
	}
	
	public List<TransactionDto> entityListToDtoList(List<Transaction> daos){
		return daos.stream().map(d -> this.entityToDto(d)).collect(Collectors.toList());
	}
	
	public List<Transaction> dtoListToEntityList(List<TransactionDto> dtos){
		return dtos.stream().map(d -> this.dtoToEntity(d)).collect(Collectors.toList());
	}
	
}
