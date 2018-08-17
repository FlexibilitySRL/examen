package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.model.Transaction;

public class TransactionApiMapper implements EntityMapper<TransactionApi, Transaction> {

    @Override
    public TransactionApi buildApi(Transaction entity) {
        return TransactionApi.newBuilder()
                .setId(entity.getId())
                .setTransactionId(entity.getTransactionId())
                .setClient(entity.getClient())
                .setProduct(entity.getProduct())
                .setAmount(entity.getAmount())
                .setDateCreated(entity.getDateCreated())
                .setApproved(entity.getApproved())
                .build();
    }

    @Override
    public Transaction buildEntity(TransactionApi api) {
        return new Transaction(
                api.getId(),
                api.getTransactionId(),
                api.getDateCreated(),
                api.getClient(),
                api.getProduct(),
                api.getAmount());
    }
}
