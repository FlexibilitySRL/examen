package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.repository.AuthorizationRepository;
import ar.com.flexibility.examen.domain.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {
    private Logger logger = LoggerFactory.getLogger("ar.com.flexibility.examen.domain.service.impl.AuthorizationServiceImpl");
    private AuthorizationRepository authorizationRepository;
    private PurcharseServiceImpl purcharseService;

    public AuthorizationServiceImpl(AuthorizationRepository authorizationRepository, PurcharseServiceImpl purcharseService) {
        this.authorizationRepository = authorizationRepository;
        this.purcharseService = purcharseService;
    }

    @Override
    public Transaction authorize(Purcharse purcharse) {
        Transaction transaction = new Transaction();

        transaction.authorize(purcharse);
        purcharseService.updatePurcharse(purcharse);

        int lastTransactionIndex = purcharse.getTransactions().size()-1;
        checkServiceStatus(purcharse.getTransactions().get(lastTransactionIndex), "The transaction was saved successfully.","An error ocurred while trying to save the transaction.");

        return purcharse.getTransactions().get(lastTransactionIndex);
    }

    private void checkServiceStatus(Transaction transaction, String infoMessage, String warningMessage) {
        if (transaction != null) {
            logger.info(infoMessage);
        } else {
            logger.warn(warningMessage);
        }
    }

    @Override
    public Transaction findById(Long id) {
        Transaction searchedTransaction = authorizationRepository.findOne(id);

        checkServiceStatus(searchedTransaction, "The authorization was searched successfully.","An error ocurred while trying to search the authorization.");

        return searchedTransaction;
    }

    @Override
    public void deleteAuthorization(Long id) {
        authorizationRepository.delete(id);

        Transaction searchedTransaction = findById(id);

        checkSuccessfullyDelete(searchedTransaction);
    }

    private void checkSuccessfullyDelete(Transaction transaction) {
        if (transaction == null) {
            logger.info("The transaction was deleted successfully");
        } else {
            logger.warn("An error ocurred while deleting the transaction");
        }
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = (List<Transaction>) authorizationRepository.findAll();

        checkSuccessfullyFindAll(transactions);

        return transactions;
    }

    private void checkSuccessfullyFindAll(List<Transaction> allTransactions) {
        if (allTransactions != null) {
            logger.info("The authorizations were found successfully");
        } else {
            logger.warn("An error ocurred while searching the authorizations");
        }
    }
}
