package ar.com.flexibility.examen.domain.service;

import java.util.Set;

import ar.com.flexibility.examen.domain.dto.TransactionDTO;
import ar.com.flexibility.examen.domain.model.Transaction;

public interface TransactionService {

	public Set<TransactionDTO> findAll();

	public TransactionDTO findById(Long id);

	public TransactionDTO save(TransactionDTO transactionDto);

	public Boolean delete(Long id);

	public TransactionDTO entityToDto(Transaction entity);

	public Transaction dtoToEntity(TransactionDTO dto);

}
