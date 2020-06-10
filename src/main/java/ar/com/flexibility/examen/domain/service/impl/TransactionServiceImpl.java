package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.enums.TransactionActions;
import ar.com.flexibility.examen.domain.enums.TransactionStatus;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.mappers.EntityApiMapper;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.repositories.ProductRepository;
import ar.com.flexibility.examen.domain.repositories.SellerRepository;
import ar.com.flexibility.examen.domain.repositories.TransactionRepository;
import ar.com.flexibility.examen.domain.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private TransactionRepository transactionRepository;
    private SellerRepository sellerRepository;
    private ProductRepository productRepository;
    private EntityApiMapper entityApiMapper;

    @Override
    public List<TransactionApi> list(Long sellerId) {
        if (!sellerRepository.exists(sellerId)) {
            throw new NotFoundException("Seller with id " + sellerId + " not found");
        }
        List<Transaction> list = transactionRepository.findBySellerId(sellerId);
        logger.info("Retrieving transactions of seller {}", sellerId);
        return entityApiMapper.sourceToDestinationTransactions(list);
    }

    @Override
    public TransactionApi processAction(Long id, String action) {
        logger.debug("Validating action {}", action);
        if (!TransactionActions.APPROVE.getCode().equals(action)) {
            throw new BadRequestException("Invalid action " + action + ". Accepted actions are: " + TransactionActions.APPROVE.getCode());
        }
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction with id " + id));
        transaction.setStatus(TransactionStatus.APPROVED.getCode());

        transaction = transactionRepository.save(transaction);
        logger.info("Transaction {} updated according to action {}", transaction.getId(), action);
        return entityApiMapper.sourceToDestinationTransaction(transaction);
    }

    @Override
    public TransactionApi create(TransactionApi transactionApi) {
        logger.debug("Validating transaction required data");
        if (!sellerRepository.exists(transactionApi.getSellerId())) {
            throw new NotFoundException("Seller with id " + transactionApi.getSellerId() + " not found");
        }
        if (!productRepository.exists(transactionApi.getProductId())) {
            throw new NotFoundException("Product with id " + transactionApi.getProductId() + " not found");
        }
        if (transactionApi.getPrice() == null || transactionApi.getPrice() <= 0) {
            throw new BadRequestException("Invalid price " + transactionApi.getProductId());
        }

        Transaction transaction = entityApiMapper.destinationToSourceTransactionApi(transactionApi);

        logger.debug("Adding additional data");
        transaction.setStatus(TransactionStatus.PENDING.getCode());
        transaction.setDate(ZonedDateTime.now());

        Transaction created = transactionRepository.save(transaction);
        logger.info("Transaction created: {}", created);
        return entityApiMapper.sourceToDestinationTransaction(created);
    }

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Autowired
    public void setEntityApiMapper(EntityApiMapper entityApiMapper) {
        this.entityApiMapper = entityApiMapper;
    }

    @Autowired
    public void setSellerRepository(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
