package ar.com.plug.examen.domain.service;

import org.springframework.data.domain.Page;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ConflictResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.Transaction.TransactionStatusEnum;

public interface TransactionService {

	Transaction findById(Long id) throws ResourceNotFoundException;

	Page<Transaction> findAll(int pageNumber, int rowPerPage);

	Page<Transaction> findAllByStatus(TransactionStatusEnum status, int pageNumber, int rowPerPage);

	Transaction save(Transaction transaction) throws BadResourceException;

	
	Transaction updateStatus(Long id, TransactionStatusEnum status)
			throws ResourceNotFoundException, ConflictResourceException;

	void deleteById(Long id) throws ResourceNotFoundException, BadResourceException;

}