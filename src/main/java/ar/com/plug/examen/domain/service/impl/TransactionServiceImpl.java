package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.execptions.BadRequestException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.repositories.TransactionRepository;
import ar.com.plug.examen.domain.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	@Override
    public TransactionApi createTransaction(TransactionApi transactionApi) {

        transactionApi.setStatus(TransactionStatusEnum.PENDING.getCode());
        transactionApi.setDate(LocalDateTime.now());

        Transaction transaction = transactionRepository.save(modelMapper.map(transactionApi, Transaction.class));
        log.info("The transaction " + transaction.getId() + " was succesfully created.");

        return modelMapper.map(transaction, TransactionApi.class);
    }

    @Override
    public TransactionApi getTransactionById(Long id) throws NotFoundException {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("", "The transaction with the id:" + id + " was not found."));

        return modelMapper.map(transaction, TransactionApi.class);
    }

    @Override
    public List<TransactionApi> listAllTransactions() {

        List<Transaction> transactions = transactionRepository.findAll();

        if (transactions.isEmpty()) {
            log.info("The list of transactions is empty");
        }
        return transactions
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionApi.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<TransactionApi> getTransactionBySellerId(Long id) {

        List<Transaction> transactions = transactionRepository.findBySellerId(id);

        if (transactions.isEmpty()) {
            log.info("The list of transactions is empty");
        }
        return transactions
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionApi.class))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionApi approveTransaction(Long id, String validation) throws BadRequestException, NotFoundException {

        if (!TransactionStatusEnum.APROVED.getCode().equals(validation)) {
            log.error("The validation is not accepted. The accepted code is " + TransactionStatusEnum.APROVED.getCode());
            throw new BadRequestException("", "The validation is not accepted. The accepted code is " + TransactionStatusEnum.APROVED.getCode());
        }

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("", "Transaction with id " + id + " was not found."));

        transaction.setStatus(TransactionStatusEnum.APROVED.getCode());
        transaction = transactionRepository.save(transaction);
        log.info("The transaction " + id + " was succesfully updated with the status of: " + validation);

        return modelMapper.map(transaction, TransactionApi.class);
    }

}














