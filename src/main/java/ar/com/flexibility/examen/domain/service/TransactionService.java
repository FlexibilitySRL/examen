package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.TransactionApi;

import java.util.List;

public interface TransactionService {

    List<TransactionApi> list(Long sellerID);

    TransactionApi processAction(Long id, String action);

    TransactionApi create(TransactionApi transactionApi);
}
