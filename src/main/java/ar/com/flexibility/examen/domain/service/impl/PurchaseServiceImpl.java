package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repo.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    
    @Autowired
    PurchaseRepository repo;
    
    @Override
    public Purchase createPurchase(Customer customer, Product product) {
        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProduct(product);
        purchase.setAporoved(false);
        
        return repo.save(purchase);
    }

    @Override
    public boolean approve(Purchase purchase) {
        purchase.setAporoved(true);
        try {
            repo.save(purchase);
            return true;
        }
        catch (Exception e) {
            purchase.setAporoved(false);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Purchase> listPurchases(Customer customer, Product product, boolean approved) {
        return repo.findByCustomerAndProduct(customer, product);
    }
    
}
