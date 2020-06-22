package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.TransactionApi;

import java.util.List;

public interface TransactionService {
    /**
     * Creates a new transaction
     *
     * @param vendorId       the vendor of the transaction
     * @param transactionApi with the values to be created
     * @return TransactionApi
     */
    TransactionApi create(Long vendorId, TransactionApi transactionApi);

    /**
     * Retrieves a list of transaction
     *
     * @param vendorId that initiated the transactions
     * @return List<TransactionApi>
     */
    List<TransactionApi> allByVendor(Long vendorId);

    /**
     * Retrieves a list of transaction
     *
     * @param clientId that are involved in the transaction
     * @return List<TransactionApi>
     */
    List<TransactionApi> allByClient(Long clientId);

    /**
     * Updates an existing transaction as long as it belongs to the vendor
     *
     * @param vendorId       the owner of the transaction
     * @param transactionApi the new transaction to be updated
     * @return TransactionApi
     */
    TransactionApi updateStatus(Long vendorId, TransactionApi transactionApi);
}
