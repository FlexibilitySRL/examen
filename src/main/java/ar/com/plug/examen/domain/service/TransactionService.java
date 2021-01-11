package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.TransactionApi;

public interface TransactionService {
	
	public TransactionApi createTransaction(TransactionApi transactionApi);

	public TransactionApi getTransactionById(Long id);
	
	public List<TransactionApi> getTransactionBySellerId(Long id);
	
	public List<TransactionApi> listAllTransactions();
	
	public TransactionApi approveTransaction(Long id, String validation);
	
}
