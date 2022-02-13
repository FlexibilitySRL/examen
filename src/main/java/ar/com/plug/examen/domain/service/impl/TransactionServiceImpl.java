package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.exception.TransactionNotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionProduct;
import ar.com.plug.examen.domain.model.TransactionStatus;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final VendorService vendorService;

    @Override
    @Transactional
    public Transaction save(TransactionApi transactionApi) {
        Client client = clientService.findById(transactionApi.getClientId());
        Vendor vendor = vendorService.findById(transactionApi.getVendorId());

        Transaction transaction = new Transaction(new Date(), transactionApi.getType(), TransactionStatus.NEW, client, vendor);

        transaction.setProducts(transactionApi.getProducts().stream().map(item -> {
            Product product = productService.findById(Long.parseLong(item.get("product")));
            return new TransactionProduct(product, Double.parseDouble(item.get("amount")), transaction);
        }).collect(Collectors.toSet()));

        return transactionRepository.save(transaction);

    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findById(long id) {
        return transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
    }
}
