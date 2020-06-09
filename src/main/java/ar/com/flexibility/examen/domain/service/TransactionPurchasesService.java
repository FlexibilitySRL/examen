package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.dto.TransactionPurchasesDTO;

public interface TransactionPurchasesService {
    TransactionPurchasesDTO create(TransactionPurchasesDTO transactionPurchasesDto);
    TransactionPurchasesDTO findByTransaction(String transactionId);
    boolean existTransactionId(String transactionId);
}
