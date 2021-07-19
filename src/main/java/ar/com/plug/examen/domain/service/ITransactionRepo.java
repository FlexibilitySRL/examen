package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.domain.model.TransactionDTORequest;
import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Transaction;

public interface ITransactionRepo {
	
	public TransactionDTO save(TransactionDTORequest request) throws BadRequestError, ResourceNotFoundError;
	
	public List<TransactionDTO> findAll();

	public TransactionDTO getTransactionByID(Long id) throws ResourceNotFoundError;
	
	public void rejectStatus(Long id) throws ResourceNotFoundError;
	
	public void approveStatus(Long id) throws ResourceNotFoundError;

	public void delete(long id) throws ResourceNotFoundError;
}
