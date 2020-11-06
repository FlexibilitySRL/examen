package ar.com.plug.examen.service;

import ar.com.plug.examen.dao.ProductDAO;
import ar.com.plug.examen.dao.TransactionsDAO;
import ar.com.plug.examen.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {

    private final TransactionsDAO transactionsDAO;
    private final ProductDAO productDAO;

    @Autowired
    public TransactionsService(TransactionsDAO transactionsDAO, ProductDAO productDAO) {
        this.transactionsDAO = transactionsDAO;
        this.productDAO = productDAO;
    }

    public int createTransaction(Transactions transactions) {
        try {
            transactionsDAO.save(transactions);
            updateProductQuantity(transactions);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateProductQuantity(Transactions transactions){
        try {
            transactions.getTransactionsProducts().stream().forEach((t)->{
                productDAO.updateQuantity(t.getId_product(),t.getQuantity());
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateTransactionState(Long id, String stateOrder){
        int result = transactionsDAO.updateStateOrder(stateOrder,id);
        return result;
    }

    public List<Transactions> getAllSellers() {
        List<Transactions> transactions = transactionsDAO.findAll();
        return transactions;
    }
}
