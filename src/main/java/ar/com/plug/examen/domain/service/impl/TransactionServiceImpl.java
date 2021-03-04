package ar.com.plug.examen.domain.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.Enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import ar.com.plug.examen.domain.Exeptions.NotFoundException;
import ar.com.plug.examen.domain.Repository.ClientRepository;
import ar.com.plug.examen.domain.Repository.ProductRepository;
import ar.com.plug.examen.domain.Repository.SellerRepository;
import ar.com.plug.examen.domain.Repository.TransactionDetailRepository;
import ar.com.plug.examen.domain.Repository.TransactionRepository;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionDetail;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.service.ValidatorService;

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
	ValidatorService validators;

	/**
	 * The complete list of existent transactions
	 * @param void
	 * @return List<TransactionApi>
	 */
	@Override
	@Transactional
	public List<TransactionApi> listAll() {
		logger.info("Serching requested data"); //a better approach coud be getting all the messages form a MESSAGES file
		List<TransactionApi> result = converter.convertList(transactionRepository.findAll(), TransactionApi.class);
		logger.info("Success!");
		return result;
	}

	/**
	 * Persists a new transaction
	 * The provided client, seller and products must already exist
	 * Receives the new transaction Returns the saved transaction with its entire detail
	 * @param TransactionApi transaction
	 * @return TransactionApi
	 */
	@Override
	@Transactional
	public TransactionApi save(TransactionApi transaction) throws BadRequestException, NotFoundException {
		logger.info("Validating data...");
		validators.checkCompleteObject(transaction, true);
		logger.info("Validation successful!");

		logger.info(String.format("Searching %1$s", "Client"));
		Client client = clientRepository.findById(transaction.getClient().getId())
				.orElseThrow(() -> new NotFoundException("Client"));
		logger.info("Found");
		logger.info(String.format("Searching %1$s", "Seller"));
		Seller seller = sellerRepository.findById(transaction.getSeller().getId())
				.orElseThrow(() -> new NotFoundException("Seller"));
		logger.info("Found!");

		logger.info(String.format("Searching %1$s", "Product"));
		List<TransactionDetail> detail = converter.convertList(transaction.getDetail(), TransactionDetail.class);
		List<Long> requestedProducts = detail.parallelStream().map(item -> item.getProduct().getId()).collect(Collectors.toList());
		List<Product> existingProducts = productRepository.findAllById(requestedProducts);
		if (requestedProducts.size() != existingProducts.size()) {
    		throw NotFoundException.unableToFindException("Product");
		}
		logger.info("Validation Successful!");

		Transaction newToPersist = new Transaction(client, seller, detail, TransactionStatusEnum.PENDING, Calendar.getInstance().getTime());

		newToPersist.getDetail().stream().forEach(item -> item.setTransaction(newToPersist));
		logger.info(String.format("Preparing for persistence of a %1$s ...", ENTITY));
		Transaction persisted = transactionRepository.saveAndFlush(converter.convert(newToPersist, Transaction.class));
		logger.info(String.format("%1$s successfully created with id: %2$s!", ENTITY, persisted.getId()));

		return converter.convert(persisted, TransactionApi.class);
	}

	/**
	 * Removes an existing transaction by its id
	 * @param long transactionId
	 * @return void
	 * @throw NotFoundException
	 */
	@Override
	@Transactional
	public void deleteById(long id) throws NotFoundException {
    	try {
			logger.info(String.format("Preparing for deletion of a %1$s ...", ENTITY));
			Transaction persisted = transactionRepository.findById(id).get();
	    	transactionRepository.delete(persisted);
	    	logger.info(String.format("%1$s successfully deleted!", ENTITY)); 
		} catch (NoSuchElementException nse) {
    		throw NotFoundException.unableToFindException("Transaction");
		}
    }

	/**
	 * Searches an existing transaction by its id and sets a new status
	 * The provided status must be different from the current one, and the new status must be a valid one.
	 * Receives the transaction id and the new status, returns the updated transaction
	 * 
	 * @param long id
	 * @param TransactionStatusEnum status
	 * @return TransactionApi transaction
	 */
	@Override
	@Transactional
	public TransactionApi updateTransactionStatusById(long id, TransactionStatusEnum status) throws NotFoundException, BadRequestException {
		logger.info(String.format("Searching %1$s", ENTITY));

		Transaction persisted = transactionRepository.findOneById(id);
		if (Objects.isNull(persisted)) {
			throw new NotFoundException(String.format("Transaction %1$s not found", ENTITY ));
		}
		logger.info("Found!");

		logger.info("Validating data");
		validators.validateTransactionStatus(persisted, status);
		logger.info("Validation successful");

		persisted.setStatus(status);

		logger.info(String.format("Preparing for update of a %1$s ...", ENTITY));
		Transaction updated = transactionRepository.save(converter.convert(persisted, Transaction.class));
		logger.info(String.format("%1$s with id: %2$s - successfully updated!", ENTITY, updated.getId()));
		return converter.convert(updated, TransactionApi.class);
	}

	/**
	 * Multiplies every product on a transaction detail by the declared quantity
	 * Receives the transaction id and returns the total amount of the transaction
	 * @param long transactionId
	 * @return Double totalAmount
	 * @throw NotFoundException
	 */
	@Override
	@Transactional
	public Double totalAmountByTransactionId(long id) throws NotFoundException {
		try {
			logger.info("Searching requested data...");
			Transaction result = transactionRepository.findById(id).get();

			Double total = result.getDetail().stream()
					.mapToDouble(detalle -> detalle.getQuantity() * detalle.getProduct().getPrice())
					.sum(); 
			logger.info("Success!");

			return total;
		} catch (IllegalArgumentException | NoSuchElementException iae) {
			throw new NotFoundException(ENTITY);
		}
	}
}
