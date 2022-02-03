package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionItem;

import java.util.ArrayList;
import java.util.List;

public interface TransactionService {
    public List<Transaction> findTransactionAll();
    public Transaction createTransaction(Transaction transaction);
    public Transaction updateTransaction(Transaction transaction);
    public Transaction deleteTransaction(Transaction transaction);
    public Transaction getTransaction(Long id);
    public Transaction addItemsToTransaction(Long id, List<TransactionItem> items);
    public Transaction changeApproveTransaction(Long id, boolean approve);
}