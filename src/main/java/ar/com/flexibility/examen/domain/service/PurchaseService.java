package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import java.util.List;

public interface PurchaseService {
    public Purchase createPurchase(Customer customer, Product product);
    public Purchase createPurchase(Long customerId, Long productId);
    public boolean approve(Purchase purchase);
    public boolean approve(Long purchaseId);
    public List<Purchase> listPurchases(Customer customer, Product product);
    public List<Purchase> listPurchases(Long customerId, Long productId);
}
