package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityConflictException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.repository.TransactionRepository;
import ar.com.flexibility.examen.domain.service.TransactionService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class DefaultTransactionService implements TransactionService {

    private static final Boolean APPROVED_FLAG = true;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> listAll() {
        return Lists.newArrayList(transactionRepository.findAll());
    }

    @Override
    public List<Transaction> findByTransactionId(Long transactionId) throws ConstraintsViolationException {
        try {
            return transactionRepository.findByTransactionId(transactionId);
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintsViolationException(e.getMessage());
        }
    }

    @Override
    public List<Transaction> approveTransaction(Long transactionId)
            throws EntityNotFoundException, ConstraintsViolationException, EntityConflictException {
        List<Transaction> transactions = findByTransactionId(transactionId);
        if(transactions.isEmpty()) {
            throw new EntityNotFoundException(format("Transaction %s does not exist", transactionId));
        }
        if(transactions
                .stream()
                .anyMatch(Transaction::getApproved)) {
            throw new EntityConflictException(format("Transaction %s is already approved", transactionId));
        }
        List<Transaction> approvedTransactions = transactions
                .stream()
                .peek(tx -> tx.setApproved(APPROVED_FLAG))
                .collect(Collectors.toList());
        transactionRepository.save(approvedTransactions);
        return approvedTransactions;
    }
}
