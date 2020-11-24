package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;

public interface TransactionService {

	List<TransactionApi> listAll();

	List<TransactionApi> findByFilters(TransactionApi filters);

	TransactionApi save(TransactionApi transaction) throws BadRequestException, NotFoundException;

	void deleteById(long id) throws NotFoundException;

	TransactionApi updateTransactionStatusById(long id, StatusEnum status) throws NotFoundException, BadRequestException;

	Double totalAmountByTransactionId(long id) throws NotFoundException;

}