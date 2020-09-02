package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.mappers.AutoMapperApiEntity;
import ar.com.flexibility.examen.domain.mappers.AutoMapperEntityApi;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.model.TransactionStatus;
import ar.com.flexibility.examen.domain.repositories.TransactionRepository;
import ar.com.flexibility.examen.domain.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	private TransactionRepository transactionRepository;
	private AutoMapperEntityApi autoMapperEntityApi;
	private AutoMapperApiEntity autoMapperApiEntity;
	private Log log = LogFactory.getLog(TransactionServiceImpl.class);
	
	@Override
	public TransactionApi create(TransactionApi transactionApi) {
		Transaction transaction = autoMapperApiEntity.sourceToDestinationTransactionApi(transactionApi);
		transaction.setTransactionStatus(TransactionStatus.CREATED);
		transactionRepository.save(transaction);
        log.debug("Transaction created");
        return autoMapperEntityApi.sourceToDestinationTransaction(transaction);
	}
	
	@Override
	public TransactionApi get(Long id) {
		Transaction transaction = null;
		try {
			transaction = transactionRepository.findById(id)
			        .orElseThrow(() -> new NotFoundException());
	        log.debug("Get Transaction");
		} catch (NotFoundException e) {
	        log.debug("Transaction not found");
		}
        return autoMapperEntityApi.sourceToDestinationTransaction(transaction);
	}
	
	@Override
	public List<TransactionApi> getTransactions() {
		List<Transaction> transactions = transactionRepository.findAll();
        return autoMapperEntityApi.sourceToDestinationTransactions(transactions);
	}
	
	@Override
	public void delete(Long id) {
		transactionRepository.delete(id);
	}
	
	@Override
	public TransactionApi update(Long id, TransactionApi transactionApi) {
		Transaction transaction = null;
		try {
			transaction = transactionRepository.findById(id)
			        .orElseThrow(() -> new NotFoundException());
			transaction.setNumber(transactionApi.getNumber());
			transaction.setClientId(transactionApi.getClientId());
			transaction.setProductId(transactionApi.getProductId());
			transaction.setDate(transactionApi.getDate());
			transactionRepository.saveAndFlush(transaction);
	        log.debug("Update transaction");
		} catch (NotFoundException e) {
	        log.debug("Transaction not found");
		}
        return autoMapperEntityApi.sourceToDestinationTransaction(transactionRepository.save(transaction));
	}
	
	/***
	 *When the transaction is approved, the state change to APPROVED 
	 ***/
	@Override
	public TransactionApi approveTransaction(Long id) {
		Transaction transaction = null;
		try {
			transaction = transactionRepository.findById(id)
			        .orElseThrow(() -> new NotFoundException());
			transaction.setTransactionStatus(TransactionStatus.APPROVED);
			transactionRepository.saveAndFlush(transaction);
	        log.debug("Transaction approved");
		} catch (NotFoundException e) {
	        log.debug("Transaction not approved");
		}
        return autoMapperEntityApi.sourceToDestinationTransaction(transactionRepository.save(transaction));
	}

	
}
