package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.model.Transaction;

public interface TransactionService {

    Transaction save(TransactionApi transactionApi);

    Transaction save(Transaction transaction);

    Transaction findById(long id);
}
