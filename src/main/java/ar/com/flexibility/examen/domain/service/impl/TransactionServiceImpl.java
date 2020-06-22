package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.mappers.ApiMapper;
import ar.com.flexibility.examen.domain.mappers.EntityMapper;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.model.Vendor;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.TransactionRepository;
import ar.com.flexibility.examen.domain.repository.VendorRepository;
import ar.com.flexibility.examen.domain.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ApiMapper apiMapper;

    @Override
    public TransactionApi create(Long vendorId, TransactionApi transactionApi) {
        log.info("Creating a new transaction for vendorId {} with data: {}", vendorId, transactionApi);
        Transaction transaction = apiMapper.toTransaction(transactionApi);

        Vendor vendor = vendorRepository.findOne(vendorId);
        if (vendor == null)
            throw new NotFoundException("Vendor with id " + vendorId);

        List<Product> product = productRepository.findAll(transactionApi.getProductId());
        if (product.isEmpty())
            throw new NotFoundException("Products for ids " + transactionApi.getProductId());

        Client client = clientRepository.findOne(transactionApi.getClientId());
        if (client == null)
            throw new NotFoundException("Product with id " + transactionApi.getClientId());

        transaction.setVendor(vendor);
        transaction.setProduct(product);
        transaction.setClient(client);

        return entityMapper.toTransactionApi(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionApi> allByVendor(Long vendorId) {
        log.info("Retrieving all transactions from transaction repository for vendor id: {}", vendorId);
        if (vendorRepository.findOne(vendorId) == null)
            throw new NotFoundException("Vendor with id " + vendorId);
        return entityMapper.toTransactionsApi(transactionRepository.findByVendorId(vendorId));
    }

    @Override
    public List<TransactionApi> allByClient(Long clientId) {
        log.info("Retrieving all transactions from transaction repository for client id: {}", clientId);
        if (clientRepository.findOne(clientId) == null)
            throw new NotFoundException("Client with id " + clientId);
        return entityMapper.toTransactionsApi(transactionRepository.findByClientId(clientId));
    }

    @Override
    public TransactionApi updateStatus(Long vendorId, TransactionApi transactionApi) {
        log.info("Updating transaction for vendorId {} with data {}", vendorId, transactionApi);
        Vendor vendor = vendorRepository.findOne(vendorId);
        if (vendor == null)
            throw new NotFoundException("Vendor with id " + vendorId);
        Transaction transaction = transactionRepository.findOne(transactionApi.getId());
        if (transaction == null)
            throw new NotFoundException("Transaction with id " + transactionApi.getId());
        if (!transaction.getVendor().getId().equals(vendorId))
            throw new BadRequestException("Transaction doesn't belong to vendor");
        transaction.setStatus(transactionApi.getStatus());
        return entityMapper.toTransactionApi(transactionRepository.save(transaction));
    }
}
