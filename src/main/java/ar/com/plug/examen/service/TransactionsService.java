package ar.com.plug.examen.service;

import ar.com.plug.examen.dao.ProductDAO;
import ar.com.plug.examen.dao.TransactionsDAO;
import ar.com.plug.examen.model.Transactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {

    private final TransactionsDAO transactionsDAO;
    private final ProductDAO productDAO;
    Logger logger = LoggerFactory.getLogger(SellersService.class);

    @Autowired
    public TransactionsService(TransactionsDAO transactionsDAO, ProductDAO productDAO) {
        this.transactionsDAO = transactionsDAO;
        this.productDAO = productDAO;
    }

    public int createTransaction(Transactions transactions) {
        try {
            transactionsDAO.save(transactions);
            logger.info("createTransaction processed correctly");
            updateProductQuantity(transactions);

            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public int updateProductQuantity(Transactions transactions){
        try {
            logger.info("updateProductQuantity processed correctly");
            transactions.getTransactionsProducts().stream().forEach((t)->{
                productDAO.updateQuantity(t.getId_product(),t.getQuantity());
            });
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public int updateTransactionState(Long id, String stateOrder){
        int result = transactionsDAO.updateStateOrder(stateOrder,id);
        return result;
    }

    public List<Transactions> getAllSellers() {
        List<Transactions> transactions = transactionsDAO.findAll();
        logger.info("getAllSellers processed correctly");
        return transactions;
    }
}
