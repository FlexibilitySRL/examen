package ar.com.plug.examen.domain.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.mappers.TransactionMapper;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.domain.model.TransactionDTORequest;
import ar.com.plug.examen.domain.repositories.TransactionRepository;
import ar.com.plug.examen.domain.service.IClientRepo;
import ar.com.plug.examen.domain.service.ITransactionRepo;
import ar.com.plug.examen.domain.validators.Validator;
import ar.com.plug.examen.entities.Transaction;

@Service
public class TransactionServiceImpl implements ITransactionRepo {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private TransactionMapper transactionMapper;
	
	@Autowired
	private IClientRepo clientService;

	@Override
	@Transactional	
	public TransactionDTO save(TransactionDTORequest request) throws BadRequestError, ResourceNotFoundError {
		this.validator.validateTransaction(request);
		ClientDTO client = this.clientService.findClientById(request.getClientId());
		TransactionDTO transactionDTO = new TransactionDTO(null, client, TransactionStatusEnum.PENDIENTE);
		return this.transactionMapper.transactionToTransactionDTO(this.transactionRepository.save(this.transactionMapper.transactionDTOtoTransaction(transactionDTO)));
	}

}
