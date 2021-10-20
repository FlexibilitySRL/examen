package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {

     TransactionDTO save(TransactionDTO transactionDTO);
     TransactionDTO approveTransaction(TransactionDTO transactionDTO);
     Optional<TransactionDTO> findTransactionById(Long id);
     Optional<List<TransactionDTO>> findApprovedTransactions();


}
