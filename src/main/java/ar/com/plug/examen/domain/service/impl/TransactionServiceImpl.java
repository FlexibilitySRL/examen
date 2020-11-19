package ar.com.plug.examen.domain.service.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionDetail;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.repository.specification.TransactionSpecification;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.Messages;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.service.ValidatorsService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	SellerService sellerService;
	
	@Autowired
	ClientService clientService;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorsService validators;
	
	@Override
	public List<TransactionApi> listAll() {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		return converter.convertList(transactionRepository.findAll(), TransactionApi.class);
	}

	@Override
	public List<TransactionApi> findByFilters(TransactionApi filters) {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		Specification<Transaction> specifications = TransactionSpecification.especificacionClientId(filters.getClient())
				.and(TransactionSpecification.especificacionClientName(filters.getClient()))
				.and(TransactionSpecification.especificacionSellerId(filters.getSeller()))
				.and(TransactionSpecification.especificacionSellerName(filters.getSeller()))
				.and(TransactionSpecification.especificacionStatus(filters.getStatus()))
				.and(TransactionSpecification.especificacionDate(filters.getDate()));

		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		return converter.convertList(transactionRepository.findAll(specifications), TransactionApi.class);
	}

	@Override
	public TransactionApi save(TransactionApi transaction) throws BadRequestException, NotFoundException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.validateTransaction(transaction, false);

		Client client = converter.convert(clientService.findById(transaction.getClient().getId()), Client.class);
		Seller seller = converter.convert(sellerService.findById(transaction.getSeller().getId()), Seller.class);
		List<TransactionDetail> detail = converter.convertList(transaction.getTransactionDetail(), TransactionDetail.class);

		Transaction newToPersist = new Transaction.Builder()
				.setClient(client)
				.setSeller(seller)
				.setDate(Calendar.getInstance().getTime())
				.setStatus(Transaction.STATUS_PENDING)
				.setTransactionDetail(detail)
				.build();

		newToPersist.getTransactionDetail().stream().forEach(item -> item.setTransaction(newToPersist));
		
		Transaction persisted = transactionRepository.save(converter.convert(newToPersist, Transaction.class));
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_CREATED, "Transaction", persisted.getId());
		logger.info(successMsg);

		return converter.convert(persisted, TransactionApi.class);
	}

	@Override
	public void deleteById(Long id) throws NotFoundException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		this.validateExistence(id);
    	
        transactionRepository.deleteById(id);
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_DELETED, "Product"); 
		logger.info(successMsg);
	}

	@Override
	public TransactionApi updateTransactionStatusById(Long id, String status) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		Transaction persisted = transactionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, "Transaction")));

		validators.validateTransactionStatus(persisted, status);

		persisted.setStatus(status);

		Transaction updated = transactionRepository.save(converter.convert(persisted, Transaction.class));
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_UPDATED, "Transaction", updated.getId()); 
		logger.info(successMsg);

		return converter.convert(updated, TransactionApi.class);
	}


	/** Validates the existence of the product to be deleted - If exists its removed **/
	private void validateExistence(Long id) throws NotFoundException {
		if (!transactionRepository.findById(id).isPresent()) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, "Transaction");
			logger.error(errorMsg);
			throw new NotFoundException(errorMsg);
		}
	}
}