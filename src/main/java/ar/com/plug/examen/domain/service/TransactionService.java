package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.execptions.BadRequestException;
import ar.com.plug.examen.domain.execptions.NotFoundException;

import java.util.List;

public interface TransactionService {
	
	TransactionApi createTransaction(TransactionApi transactionApi);

	TransactionApi getTransactionById(Long id) throws NotFoundException;
	
	List<TransactionApi> getTransactionBySellerId(Long id);
	
	List<TransactionApi> listAllTransactions();
	
	TransactionApi approveTransaction(Long id, String validation) throws BadRequestException, NotFoundException;
	
}
