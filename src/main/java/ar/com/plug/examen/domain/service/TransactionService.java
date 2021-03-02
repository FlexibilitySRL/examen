package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.Enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import javassist.NotFoundException;

public interface TransactionService {
	TransactionApi updateTransactionStatusById(long id, TransactionStatusEnum status) throws NotFoundException, BadRequestException;

	List<TransactionApi> listAll();

	TransactionApi save(TransactionApi transaction) throws BadRequestException, NotFoundException;

	void deleteById(long id) throws NotFoundException;


	Double totalAmountByTransactionId(long id) throws NotFoundException;
}
