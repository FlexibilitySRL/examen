package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityConflictException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.Transaction;

import java.util.List;

/**
 * Represents the service that manages the {@link Transaction} operations.
 */
public interface TransactionService {

    /**
     * Lists all the {@link Transaction} present in database.
     * @return {@link Transaction} registered in database.
     */
    List<Transaction> listAll();

    /**
     * Lists all the {@link Transaction} identified by transactionId.
     * @param transactionId that identifies the requested {@link Transaction}.
     * @return {@link Transaction} registered in database filtered by transactionId.
     * @throws ConstraintsViolationException if something goes wrong.
     */
    List<Transaction> findByTransactionId(Long transactionId) throws ConstraintsViolationException;

    /**
     * Approves a transaction.
     * @param transactionId that identifies the requested {@link Transaction}.
     * @return approved {@link Transaction}.
     * @throws EntityNotFoundException if the {@link Transaction} with id does not exists in database.
     * @throws ConstraintsViolationException if something goes wrong.
     * @throws EntityConflictException if the {@link Transaction} with id is already approved.
     */
    List<Transaction> approveTransaction(Long transactionId) throws EntityNotFoundException, ConstraintsViolationException, EntityConflictException;
}
