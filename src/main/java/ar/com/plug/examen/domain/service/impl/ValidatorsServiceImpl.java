package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionDetailApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.Messages;
import ar.com.plug.examen.domain.service.ValidatorsService;

@Service
@Transactional
public class ValidatorsServiceImpl implements ValidatorsService {

	private final Logger logger = LoggerFactory.getLogger(ValidatorsServiceImpl.class);
	
	@Autowired
	ClientService clienteService;

	/** Validates the status update is valid 
	 * Also validates the transactions current state is different **/
	@Override
	public void validateTransactionStatus(Transaction transaction, String status) throws BadRequestException {
		if (Objects.isNull(status)) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "Transaction - status");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		
		if (!Transaction.ALL_STATUSES.contains(status)) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_INVALID_TRANSACTION_STATUS, status);
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		
		if (transaction.getStatus().equals(status)) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_TRANSACTION_STATUS_ALREADY, status);
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
	}

	/** Validates the existence of the given id **/
	@Override
	public void validateEntityId(Long id, String type) throws BadRequestException {
		if (Objects.isNull(id) || !(id instanceof Long)) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, type + " - id");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
	}

	/** Validates the provided client **/
	@Override
	public void validateTransactionClient(ClientApi client) throws BadRequestException {
		if (Objects.isNull(client) || Objects.isNull(client.getId())) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "client");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
	}

	/** Validates the provided seller **/
	@Override
	public void validateTransactionSeller(SellerApi seller) throws BadRequestException {
		if (Objects.isNull(seller) || Objects.isNull(seller.getId())) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "seller");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
	}

	/** Validates the provided quantity for each product on the detail **/
	@Override
	public void validateProductQuantity(int quantity) throws BadRequestException {
		if (quantity <= 0) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_INVALID_PRODUCT_QUANTITY, quantity);
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
	}
	/** Validates the transaction has a detail (has at least a product)
	 * Also validates every listed product has an Id **/
	@Override
	public void validateTransactionDetail(List<TransactionDetailApi> transactionDetail) throws BadRequestException {
		if (Objects.isNull(transactionDetail) || transactionDetail.isEmpty()) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "transaction - products");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		} else {
			for (TransactionDetailApi detail : transactionDetail) {
				validateEntityId(detail.getProduct().getId(), "product");
				validateProductQuantity(detail.getQuantity());
			}
		}
	}

	/** Groups all the validations required to persist or merge a transaction **/
	@Override
	@Transactional
	public void validateTransaction(TransactionApi transaction, boolean isUpdate) throws BadRequestException {
		/** Only validates the transaction has an Id when the requested operation is an update **/
		if (isUpdate) {
			this.validateEntityId(transaction.getId(), "transaction");
		}
		/** A transaction has to be linked to a Client **/
		this.validateTransactionClient(transaction.getClient());
		/** A transaction has to be linked to a Seller **/
		this.validateTransactionSeller(transaction.getSeller());
		/** A transaction must contain a detail (products) **/
		this.validateTransactionDetail(transaction.getTransactionDetail());
	}
	
	@Override
    public void validateClient(ClientApi client, boolean isUpdate) throws BadRequestException {
    	/** Validates the existence of the id attribute **/
		if (Objects.isNull(client.getId()) && isUpdate) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "client - id");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		
    	/** Validates the existence of the name attribute **/
		if (Strings.isNullOrEmpty(client.getName())) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "client - name");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
    }

	@Override
	public void validateSeller(SellerApi seller, boolean isUpdate) throws BadRequestException {
		/** Validates the existence of the id attribute **/
		if (Objects.isNull(seller.getId()) && isUpdate) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "seller - id");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}

		/** Validates the existence of the name attribute **/
		if (Strings.isNullOrEmpty(seller.getName())) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "seller - name");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
	}

	@Override
    public void validateProduct(ProductApi product, boolean isUpdate) throws BadRequestException {
    	/** Validates the existence of the id attribute **/
		if (Objects.isNull(product.getId()) && isUpdate) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "product - id");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		
    	/** Validates the existence of the name attribute **/
		if (Strings.isNullOrEmpty(product.getName())) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "product - name");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		
    	/** Validates the existence of the price attribute **/
		if (Objects.isNull(product.getPrice()) || Double.isInfinite(product.getPrice()) || Double.isNaN(product.getPrice())) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "product - price");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
    }
}