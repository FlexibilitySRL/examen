package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.PurchaseApi;
import ar.com.plug.examen.app.api.PurchaseTransactionApi;
import ar.com.plug.examen.domain.common.impl.RequestTool;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.PurchaseUseCase;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PurchaseServiceImpl implements PurchaseUseCase {
    @Autowired
    private PurchaseRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * This method allows save purchase
     * @param purchase
     * @return @{@link PurchaseApi}
     *
     */
    @Override
    public PurchaseApi save(PurchaseApi purchase) throws ResourceNotFoundException{
        log.info("Save Purchase begins");
        Product product = productRepository.findProductById(purchase.getIdProduct()).orElseThrow(
                () ->  {
                    log.info("Product not found with Id " + purchase.getIdProduct());
                    return new ResourceNotFoundException("Product not found with Id " + purchase.getIdProduct());
                });
        Customer customer = customerRepository.findCustomerById(purchase.getIdCustomer())
                .orElseThrow(
                () -> {
                    log.info("User not found with userId " + purchase.getIdCustomer());
                    return new ResourceNotFoundException("User not found with userId " + purchase.getIdCustomer());
                });
        Purchase purchaseEntity = new Purchase.Builder()
                .withIdCustomer(customer)
                .withIdProduct(product)
                .withIdWorker(purchase.getIdWorker())
                .withCreated(new Date(System.currentTimeMillis()))
                .build();
        purchaseEntity = repository.save(purchaseEntity);
        log.info("Purchase saved");
        purchase.setId(purchaseEntity.getId());
        return purchase;
    }

    /**
     * This method allows to get the purchase's transactiones
     * @param idWorker
     * @return @{@link PurchaseTransactionApi}
     */
    @Override
    public List<PurchaseTransactionApi> getTransaction(Long idWorker) {
        log.info("get Purchase Transaction begins");
        return repository.findPurchaseByIdWorker(idWorker).stream().map(purchase -> {
            PurchaseTransactionApi purchaceApi = new PurchaseTransactionApi();
            purchaceApi.setId(purchase.getId());
            purchaceApi.setIdCustomer(RequestTool.parseCustomer().apply(purchase.getIdCustomer()));
            purchaceApi.setIdProduct(RequestTool.parseProduct().apply(purchase.getIdProduct()));
            purchaceApi.setIdWorker(purchase.getIdWorker());
            return purchaceApi;
        }).collect(Collectors.toList());
    }
}
