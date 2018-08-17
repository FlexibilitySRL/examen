package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.domain.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> listAll();

    List<Transaction> findByTransactionId(Long transactionId) throws ConstraintsViolationException;
}
