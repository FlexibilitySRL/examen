package ar.com.plug.examen.domain.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionDetail;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.repository.TransactionDetailRepository;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.repository.specification.TransactionSpecification;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.Messages;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.service.ValidatorsService;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	private final static String ENTITY = "Transaction";

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	TransactionDetailRepository transactionDetailRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorsService validators;
	
	/**
	 * @return The complete list of existent transactions
	 */
	@Override
	@Transactional
	public List<TransactionApi> listAll() {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		List<TransactionApi> result = converter.convertList(transactionRepository.findAll(), TransactionApi.class);
		logger.info(Messages.MSG_SUCCESS);
		return result;
	}

	/**
	 * @return A filtered list of transactions
	 */
	@Override
	@Transactional
	public List<TransactionApi> findByFilters(TransactionApi filters) {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		Specification<Transaction> specifications = TransactionSpecification.especificacionClientId(filters.getClient())
				.and(TransactionSpecification.especificacionClientName(filters.getClient()))
				.and(TransactionSpecification.especificacionSellerId(filters.getSeller()))
				.and(TransactionSpecification.especificacionSellerName(filters.getSeller()))
				.and(TransactionSpecification.especificacionStatus(filters.getStatus()))
				.and(TransactionSpecification.especificacionDate(filters.getDate()));
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		List<TransactionApi> result = converter.convertList(transactionRepository.findAll(specifications), TransactionApi.class);
		logger.info(Messages.MSG_SUCCESS);
		return result;
	}

	/**
	 * Persists a new transaction
	 * The provided client, seller and products must already exist
	 * @return Saved transaction with its entire detail
	 */
	@Override
	@Transactional
	public TransactionApi save(TransactionApi transaction) throws BadRequestException, NotFoundException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(transaction, true);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

		logger.info(String.format(Messages.MSG_SEARCHING, "Client"));
		Client client = clientRepository.findById(transaction.getClient().getId())
				.orElseThrow(() -> new NotFoundException("Client"));
		logger.info(Messages.MSG_FOUND);
		logger.info(String.format(Messages.MSG_SEARCHING, "Seller"));
		Seller seller = sellerRepository.findById(transaction.getSeller().getId())
				.orElseThrow(() -> new NotFoundException("Seller"));
		logger.info(Messages.MSG_FOUND);
		
		logger.info(String.format(Messages.MSG_SEARCHING, "Product"));
		List<TransactionDetail> detail = converter.convertList(transaction.getTransactionDetail(), TransactionDetail.class);
		List<Long> requestedProducts = detail.parallelStream().map(item -> item.getProduct().getId()).collect(Collectors.toList());
		List<Product> existingProducts = productRepository.findAllById(requestedProducts);
		if (requestedProducts.size() != existingProducts.size()) {
    		throw NotFoundException.unableToFindException("Product");
		}
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

		Transaction newToPersist = new Transaction.Builder()
				.setClient(client)
				.setSeller(seller)
				.setDate(Calendar.getInstance().getTime())
				.setStatus(StatusEnum.PENDING)
				.setTransactionDetail(detail)
				.build();

		newToPersist.getTransactionDetail().stream().forEach(item -> item.setTransaction(newToPersist));
		logger.info(String.format(Messages.MSG_PREPARING_PERSISTENCE, ENTITY));
		Transaction persisted = transactionRepository.saveAndFlush(converter.convert(newToPersist, Transaction.class));
		logger.info(String.format(Messages.MSG_SUCCESSFULLY_CREATED, ENTITY, persisted.getId()));

		return converter.convert(persisted, TransactionApi.class);
	}

	/**
	 * Removes an existing transaction by its id
	 */
	@Override
	@Transactional
	public void deleteById(long id) throws NotFoundException {
    	try {
			logger.info(String.format(Messages.MSG_PREPARING_DELETION, ENTITY));
			Transaction persisted = transactionRepository.findById(id).get();
	    	transactionRepository.delete(persisted);
	    	logger.info(String.format(Messages.MSG_SUCCESSFULLY_DELETED, ENTITY)); 
		} catch (NoSuchElementException nse) {
    		throw NotFoundException.unableToFindException("Transaction");
		}
    }

	/**
	 * Searches an existing transaction by its id and sets a new status
	 * The provided status must be different from the current one, and the new status must be a valid one
	 * @return The updated transaction
	 */
	@Override
	@Transactional
	public TransactionApi updateTransactionStatusById(long id, StatusEnum status) throws NotFoundException, BadRequestException {
		logger.info(String.format(Messages.MSG_SEARCHING, ENTITY));
			
		Transaction persisted = transactionRepository.findOneById(id);
		if (Objects.isNull(persisted)) {
			throw NotFoundException.unableToFindException(ENTITY);
		}
		logger.info(Messages.MSG_FOUND);
		
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.validateTransactionStatus(persisted, status);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

		persisted.setStatus(status);
		
		logger.info(String.format(Messages.MSG_PREPARING_UPDATE, ENTITY));
		Transaction updated = transactionRepository.save(converter.convert(persisted, Transaction.class));
		logger.info(String.format(Messages.MSG_SUCCESSFULLY_UPDATED, ENTITY, updated.getId()));
		return converter.convert(updated, TransactionApi.class);
	}

	/**
	 * Multiplies every product on a transaction detail by the declared quantity
	 * @return The total amount of the transaction
	 */
	@Override
	@Transactional
	public Double totalAmountByTransactionId(long id) throws NotFoundException {
		try {
			logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
			Transaction result = transactionRepository.findById(id).get();

			Double total = result.getTransactionDetail().stream()
					.mapToDouble(detalle -> detalle.getQuantity() * detalle.getProduct().getPrice())
					.sum(); 
			logger.info(Messages.MSG_SUCCESS);

			return total;
		} catch (IllegalArgumentException | NoSuchElementException iae) {
			throw NotFoundException.unableToFindException(ENTITY);
		}
	}
}