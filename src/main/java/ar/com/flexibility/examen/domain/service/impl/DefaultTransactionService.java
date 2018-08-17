package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.repository.TransactionRepository;
import ar.com.flexibility.examen.domain.service.TransactionService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTransactionService implements TransactionService {

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
}
