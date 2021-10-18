package ar.com.plug.examen.domain.repository.impl;

import ar.com.plug.examen.domain.crud.TransactionCrudRepository;
import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.mapper.TransactionMapper;
import ar.com.plug.examen.domain.model.ItemTransactionPK;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.repository.ITransactionRepository;
import ar.com.plug.examen.utils.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepository implements ITransactionRepository {

    @Autowired
    private TransactionCrudRepository transactionCrudRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionDTO save(TransactionDTO transactionDTO) {

        Transaction transaction = transactionMapper.toTransaction(transactionDTO);
        transaction.getItemsTransaction()
                .forEach(itemTransaction -> itemTransaction.setTransaction(transaction));

        return  transactionMapper.toTransactionDto(transactionCrudRepository.save(transaction));
    }

    @Override
    public TransactionDTO approveTransaction(TransactionDTO transactionDTO) {

        return save(transactionDTO);
    }

    @Override
    public Optional<TransactionDTO> findTransactionById(Long id) {
        return transactionCrudRepository.findById(id)
                  .map(transaction -> transactionMapper.toTransactionDto(transaction));

    }

    @Override
    public Optional<List<TransactionDTO>> findApprovedTransactions() {
        Optional<List<Transaction>> transactions = transactionCrudRepository.findByStateEquals(TransactionStatus.APPROVED.getCode());
        return  transactions.map(listTransactions -> transactionMapper.toListTransactionsDto(listTransactions));
 }
}


