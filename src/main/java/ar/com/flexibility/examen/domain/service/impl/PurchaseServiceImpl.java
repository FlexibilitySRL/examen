package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repo.CustomerRepository;
import ar.com.flexibility.examen.domain.repo.ProductRepository;
import ar.com.flexibility.examen.domain.repo.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    
    @Autowired
    PurchaseRepository repo;
    
    @Autowired
    CustomerRepository customerRepo;
    
    @Autowired
    ProductRepository productRepo; 
   
    @Override
    public Purchase createPurchase(Customer customer, Product product) {
        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProduct(product);
        purchase.setAporoved(false);
        
        return repo.save(purchase);
    }
    
    @Override
    public Purchase createPurchase(Long customerId, Long productId) {
        Customer customer = customerRepo.findOne(customerId);
        Product product = productRepo.findOne(productId);
        return this.createPurchase(customer, product);
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
    public boolean approve(Long purchaseId) {
        Purchase purchase = repo.findOne(purchaseId);
        return this.approve(purchase);
    }

    @Override
    public List<Purchase> listPurchases(Customer customer, Product product) {
        return repo.findByCustomerAndProduct(customer, product);
    }

    @Override
    public List<Purchase> listPurchases(Long customerId, Long productId) {
        Customer customer = customerRepo.findOne(customerId);
        Product product = productRepo.findOne(productId);
        return this.listPurchases(customer, product);
    }
}
