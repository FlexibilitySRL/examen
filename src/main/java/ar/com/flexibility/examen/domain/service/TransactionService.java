package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.rest.dto.TransactionRequestDto;
import ar.com.flexibility.examen.domain.model.Transaction;

public interface TransactionService {
	public Transaction findTransaction(Integer transactionId);

	public void createTransaction(TransactionRequestDto dto);

	public void approveTransaction(TransactionRequestDto dto);
}
