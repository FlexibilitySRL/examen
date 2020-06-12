package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import java.util.List;

public interface PurchaseService {
    public Purchase createPurchase(Customer customer, Product product);
    public boolean approve(Purchase purchase);    
    public List<Purchase> listPurchases(Customer customer, Product product, boolean approved);
}
