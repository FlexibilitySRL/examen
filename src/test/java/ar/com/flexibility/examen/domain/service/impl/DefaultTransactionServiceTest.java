package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityConflictException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTransactionServiceTest {

    private static final Long DEFAULT_ID = 1L;
    private static final Long DEFAULT_TRANSACTION__ID = 1L;
    private static final String DEFAULT_DOCUMENT_ID = "123";
    private static final String DEFAULT_NAME = "Manuel";

    private final Client client = new Client(
            DEFAULT_ID,
            new Date(),
            DEFAULT_DOCUMENT_ID,
            DEFAULT_NAME);
    private final Product product = new Product(
            DEFAULT_ID,
            new Date(),
            DEFAULT_NAME,
            BigDecimal.ONE);
    private final Transaction transaction = new Transaction(
            DEFAULT_ID,
            DEFAULT_TRANSACTION__ID,
            new Date(),
            client,
            product,
            BigDecimal.ONE);

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    DefaultTransactionService service;

    @Test
    public void listAllTest() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(transactionRepository.findAll()).thenReturn(transactions);
        List<Transaction> result = service.listAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void findByTransactionIdTest() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(transactionRepository.findByTransactionId(DEFAULT_TRANSACTION__ID))
                .thenReturn(transactions);
        List<Transaction> result = service.findByTransactionId(DEFAULT_TRANSACTION__ID);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test(expected = ConstraintsViolationException.class)
    public void findByTransactionIdConstrainErrorTest() throws Exception {
        when(transactionRepository.findByTransactionId(DEFAULT_TRANSACTION__ID))
                .thenThrow(DataIntegrityViolationException.class);
        service.findByTransactionId(DEFAULT_TRANSACTION__ID);
    }

    @Test
    public void approveTransaction() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        transaction.setApproved(false);
        transactions.add(transaction);
        when(transactionRepository.findByTransactionId(DEFAULT_TRANSACTION__ID))
                .thenReturn(transactions);
        List<Transaction> result = service.approveTransaction(DEFAULT_TRANSACTION__ID);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test(expected = EntityConflictException.class)
    public void approveAlreadyApprovedTransaction() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        transaction.setApproved(true);
        transactions.add(transaction);
        when(transactionRepository.findByTransactionId(DEFAULT_TRANSACTION__ID))
                .thenReturn(transactions);
        service.approveTransaction(DEFAULT_TRANSACTION__ID);
    }

    @Test(expected = EntityNotFoundException.class)
    public void approveNonExistingTransaction() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        when(transactionRepository.findByTransactionId(DEFAULT_TRANSACTION__ID))
                .thenReturn(transactions);
        service.approveTransaction(DEFAULT_TRANSACTION__ID);
    }
}