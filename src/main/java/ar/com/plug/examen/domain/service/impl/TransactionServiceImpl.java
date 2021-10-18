package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.repository.ITransactionRepository;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.utils.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;


    @Override
    @Transactional
    public TransactionDTO save(TransactionDTO transactionDTO) {

        transactionDTO.setState(TransactionStatus.PENDING.getCode());
        transactionDTO.setDate(LocalDateTime.now());
        return transactionRepository.save(transactionDTO);
    }

    @Override
    @Transactional
    public TransactionDTO approveTransaction(TransactionDTO transactionDTO) {

        transactionDTO.setState(TransactionStatus.APPROVED.getCode());
        return transactionRepository.approveTransaction(transactionDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionDTO> findTransactionById(Long id) {
        return transactionRepository.findTransactionById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransactionDTO>> findApprovedTransactions() {
        return transactionRepository.findApprovedTransactions();

    }
}
