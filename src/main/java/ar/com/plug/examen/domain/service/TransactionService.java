package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.TransactionDTO;



import java.util.List;
import java.util.Optional;


public interface TransactionService {

    TransactionDTO save(TransactionDTO transactionDTO);
    TransactionDTO approveTransaction(TransactionDTO transaction);
    Optional<TransactionDTO> findTransactionById(Long id);
    Optional<List<TransactionDTO>> findApprovedTransactions();
}
