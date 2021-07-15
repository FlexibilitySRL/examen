package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.domain.model.TransactionDTORequest;
import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Transaction;

public interface ITransactionRepo {
	
	public TransactionDTO save(TransactionDTORequest request) throws BadRequestError, ResourceNotFoundError;


}
