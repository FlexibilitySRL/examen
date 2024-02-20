package ar.com.plug.examen.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import ar.com.plug.examen.domain.Transaction;

public interface TransactionsService {

    public Transaction createTransaction(Transaction transaction);

    public List<Transaction> findByClientEmail(String email, Boolean approved);

    public List<Transaction> findByApproved(Boolean approved);

    public List<Transaction> findByDate(LocalDateTime fromDate, LocalDateTime toDate, Boolean approved);

    public Map<String, String> approvedTransacctions(List<String> ids);

}
