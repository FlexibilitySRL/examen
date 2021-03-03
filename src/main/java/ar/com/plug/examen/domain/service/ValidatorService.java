package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.Enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import ar.com.plug.examen.domain.model.Transaction;

public interface ValidatorService {

	void validateTransactionStatus(Transaction transaction, TransactionStatusEnum status) throws BadRequestException;

	boolean checkCompleteObject(Object object, boolean skipId) throws BadRequestException;
}
