package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.TransactionApi;

import java.util.List;

public interface TransactionService {

    TransactionApi create(TransactionApi transactionApi);

    TransactionApi get(Long id);

    List<TransactionApi> getTransactions();

    void delete(Long id);

    TransactionApi update(Long id, TransactionApi transactionApi);
    
    TransactionApi approveTransaction(Long id);
}