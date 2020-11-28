/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.repositories.TransactionRepository;
import ar.com.plug.examen.domain.service.TransactionService;
import java.util.Set;

/**
 *
 * @author msulbara
 */
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Set<Transaction> findAll() {
        return (Set<Transaction>) transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Transaction save(Transaction object) {
        return transactionRepository.save(object);
    }

    @Override
    public Transaction update(Transaction object) {
        return save(object);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public void delete(Transaction object) {
        transactionRepository.delete(object);
    }

}
