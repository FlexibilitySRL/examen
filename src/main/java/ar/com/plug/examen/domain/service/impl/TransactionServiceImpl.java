package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionItem;
import ar.com.plug.examen.domain.repository.TransactionItemsRepository;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionItemsRepository invoiceItemsRepository;



    @Override
    public List<Transaction> findTransactionAll() {
        return  transactionRepository.findAll();
    }


    @Override
    public Transaction createTransaction(Transaction transaction) {
        Optional<Transaction> transactionDB = Optional.ofNullable(transactionRepository.findByNumberTransaction(transaction.getNumberTransaction()));
        if (transactionDB.isPresent()){
            log.error("Invoice already exits: {}", transaction.getNumberTransaction());
            return  transactionDB.get();
        }
        log.info("Invoice created: {}", transaction.getNumberTransaction());
        transaction.setState("CREATED");
        return transactionRepository.save(transaction);
    }


    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Optional<Transaction> invoiceDB = Optional.ofNullable(getTransaction(transaction.getId()));
        if (invoiceDB.isEmpty()){
            log.error("Invoice not found: {}", transaction.getNumberTransaction());
            return  null;
        }
        Transaction invoiceUpdated = invoiceDB.get();
        invoiceUpdated.setApproved(transaction.isApproved());
        invoiceUpdated.setClientId(transaction.getClientId());
        invoiceUpdated.setDescription(transaction.getDescription());
        invoiceUpdated.setNumberTransaction(transaction.getNumberTransaction());
        invoiceUpdated.getItems().clear();
        invoiceUpdated.setItems(transaction.getItems());
        log.info("Invoice success updated: {}", transaction.getNumberTransaction());
        return transactionRepository.save(invoiceUpdated);
    }


    @Override
    public Transaction deleteTransaction(Transaction transaction) {
        Optional<Transaction> transactionDB = Optional.ofNullable(getTransaction(transaction.getId()));
        if (transactionDB.isEmpty()){
            log.error("Transaction not found: {}", transaction.getNumberTransaction());
            return  null;
        }
        Transaction invoiceDeleted = transactionDB.get();
        invoiceDeleted.setState("DELETED");
        log.info("Transaction success deleted: {}", transaction.getNumberTransaction());
        return transactionRepository.save(invoiceDeleted);
    }

    @Override
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction addItemsToTransaction(Long id, List<TransactionItem> items) {
        Optional<Transaction> transactionDB = Optional.ofNullable(getTransaction(id));
        if (transactionDB.isEmpty()){
            log.error("Transaction with id: {} not found", id);
            return  null;
        }
        transactionDB.get().getItems().addAll(items);
        return transactionRepository.save(transactionDB.get());
    }

    @Override
    public Transaction changeApproveTransaction(Long id, boolean approve) {
        Optional<Transaction> transactionDB = Optional.ofNullable(getTransaction(id));
        if (transactionDB.isEmpty()){
            log.error("Transaction with id: {} not found", id);
            return  null;
        }
        transactionDB.get().setApproved(approve);
        return transactionRepository.save(transactionDB.get());
    }
}