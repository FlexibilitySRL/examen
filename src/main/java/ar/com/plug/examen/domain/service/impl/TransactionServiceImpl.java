package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.api.PurchaseApprovalApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.mapper.Mapper;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.validator.ClientIdValidator;
import ar.com.plug.examen.domain.validator.ClientNameValidator;
import ar.com.plug.examen.domain.validator.DocumentValidator;
import ar.com.plug.examen.domain.validator.ListValidator;
import ar.com.plug.examen.domain.validator.ProductIdValidator;
import ar.com.plug.examen.domain.validator.ProductNameValidator;
import ar.com.plug.examen.domain.validator.ProductPriceValidator;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public TransactionApi find(Long id) throws NotExistException {
		Optional<Transaction> optional = transactionRepository.findById(id);
		if(!optional.isPresent())
			throw new NotExistException("La transaccion que quiere consultar no existe");
		
		return Mapper.mapperToTransactionApi(optional.get());
	}

	@Override
	public Long create(PurchaseApprovalApi purchaseApprovalApi) throws ValidatorException, NotExistException {
		ListValidator listValidator = new ListValidator(Arrays.asList(new ClientIdValidator(),
																	  new ClientNameValidator(),
																	  new DocumentValidator()));
		listValidator.validate(purchaseApprovalApi.getClient());
		listValidator = new ListValidator(Arrays.asList(new ProductNameValidator(),
													    new ProductPriceValidator(),
													    new ProductIdValidator()));
		listValidator.validate(purchaseApprovalApi.getProduct());
		
		
		Transaction transaction = Mapper.mapperToTransaction(purchaseApprovalApi);
		transaction.setDate(LocalDateTime.now());
		try {
			transaction = transactionRepository.save(transaction);
			return transaction.getId();
		}catch(Exception e) {
			throw new NotExistException ("Los datos ingresados no son existen en nuestra base de datos");
		}
		
	}

}
