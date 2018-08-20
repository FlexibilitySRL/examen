package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    private static final Long DEFAULT_TRANSACTION__ID = 1L;

    @Mock
    TransactionService service;

    @Mock
    Transaction entity;

    @Mock
    TransactionApi api;

    @InjectMocks
    TransactionController controller;

    @Test
    public void listTest() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(entity);
        when(service.listAll()).thenReturn(transactions);
        List<TransactionApi> result = controller.list();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void listByTransactionIdTest() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(entity);
        when(service.findByTransactionId(DEFAULT_TRANSACTION__ID)).thenReturn(transactions);
        List<TransactionApi> result = controller.list(DEFAULT_TRANSACTION__ID);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void approveTest() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(entity);
        when(service.approveTransaction(DEFAULT_TRANSACTION__ID)).thenReturn(transactions);
        List<TransactionApi> result = controller.approve(DEFAULT_TRANSACTION__ID);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}