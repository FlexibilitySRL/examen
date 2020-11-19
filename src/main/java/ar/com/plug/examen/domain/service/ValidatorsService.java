package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionDetailApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.model.Transaction;

public interface ValidatorsService {

	void validateTransactionStatus(Transaction transaction, String status) throws BadRequestException;

	void validateEntityId(Long id, String type) throws BadRequestException;

	void validateTransactionClient(ClientApi client) throws BadRequestException;

	void validateTransactionSeller(SellerApi seller) throws BadRequestException;

	void validateProductQuantity(int quantity) throws BadRequestException;

	void validateTransactionDetail(List<TransactionDetailApi> transactionDetail) throws BadRequestException;

	void validateTransaction(TransactionApi transaction, boolean isUpdate) throws BadRequestException;

	void validateClient(ClientApi client, boolean isUpdate) throws BadRequestException;

	void validateSeller(SellerApi seller, boolean isUpdate) throws BadRequestException;

	void validateProduct(ProductApi product, boolean isUpdate) throws BadRequestException;

}