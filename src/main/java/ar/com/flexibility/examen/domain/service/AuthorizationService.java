package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.model.Purcharse;

import java.util.List;

public interface AuthorizationService {

    Transaction authorize(Purcharse purcharse);

    Transaction findById(Long id);

    void deleteAuthorization(Long id);

    List<Transaction> findAll();

}
