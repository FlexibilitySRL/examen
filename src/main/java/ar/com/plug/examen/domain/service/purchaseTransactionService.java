package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.purchaseTransactionModel;
import ar.com.plug.examen.domain.repository.purchaseTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class purchaseTransactionService {

    @Autowired
    purchaseTransactionRepository purchaseTransactionRepository;

    public List<purchaseTransactionModel> getAll() {
        return (List<purchaseTransactionModel>) purchaseTransactionRepository.findAll();
    }

    public purchaseTransactionModel saveTransaction(purchaseTransactionModel purchaseTransactionModel) {

        return purchaseTransactionRepository.save(purchaseTransactionModel);
    }
}
