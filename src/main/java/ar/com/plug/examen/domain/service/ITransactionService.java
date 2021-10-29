package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.dto.TransactionDto;

public interface ITransactionService {

	public List<TransactionDto> findAll();
	
	public TransactionDto findById(Long id) throws Exception;
	
	public TransactionDto save(TransactionDto transaction);
}
