package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.execptions.BadRequestException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.mappers.TransactionMapper;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.repositories.TransactionRepository;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.validations.PairResult;
import ar.com.plug.examen.domain.validations.Validation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionMapper transactionMapper;
	
	@Autowired
	private Validation validation;

	@Override
	public TransactionApi createTransaction(TransactionApi transactionApi) {
		
		PairResult result = new PairResult(false, null);
		
		result = validation.validateTransaction(transactionApi);
		
		if(!result.isValid()) {
			log.error("Mandatory data is missing: " + result.getLeyend());
			throw new BadRequestException("Mandatory data is missing: " + result.getLeyend());
		}
		
		transactionApi.setStatus(TransactionStatusEnum.PENDING.getCode());
		transactionApi.setDate(LocalDateTime.now());

		Transaction transaction = transactionRepository.save(transactionMapper.fillEntity(new Transaction(), transactionApi));

		log.info("The transaction " + transaction.getId() +" was succesfully created.");
		
		return transactionMapper.getDto(transaction);
		
	}

	@Override
	public TransactionApi getTransactionById(Long id) {
		
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("The transaction with the id:" + id + " was not found."));
		
		return transactionMapper.getDto(transaction);
	}

	@Override
	public List<TransactionApi> listAllTransactions() {
		
		List<Transaction> transactions = transactionRepository.findAll();
		
		if(transactions.isEmpty()) {
			log.info("The list of transactions is empty");
		}
		
		return transactionMapper.getDto(transactions);
		
	}
	
	@Override
	public List<TransactionApi> getTransactionBySellerId(Long id) {
		
		List<Transaction> transactions = transactionRepository.findBySellerId(id);
		
		if(transactions.isEmpty()) {
			log.info("The list of transactions is empty");
		}
		
		return transactionMapper.getDto(transactions);
		
	}

	@Override
	public TransactionApi approveTransaction(Long id, String validation) {
		
		log.info("Validating transaction.");
		
		if(!TransactionStatusEnum.APROVED.getCode().equals(validation)){
			log.error("The validation is not accepted. The accepted code is " + TransactionStatusEnum.APROVED.getCode());
			throw new BadRequestException("The validation is not accepted. The accepted code is " + TransactionStatusEnum.APROVED.getCode());
		}
		
		Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction with id " + id + " was not found."));
		
		transaction.setStatus(TransactionStatusEnum.APROVED.getCode());
		
		transaction = transactionRepository.save(transaction);
		
		log.info("The transaction " + id +" was succesfully updated with the status of: " + validation);
		
		return transactionMapper.getDto(transaction);
	}

}














