package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.repository.PurchaseRepository;
import ar.com.plug.examen.domain.exceptions.ClientDoesNotExistException;
import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.exceptions.PurchaseDoesNotExistException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PurchaseServiceImpl implements IPurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    public PurchaseServiceImpl(PurchaseRepository repository){
        purchaseRepository = repository;
    }

    @Override
    public List<Purchase> findAll() {
        return null;
    }

    @Override
    public Purchase savePurchase(Purchase aPurchase) {
        return this.purchaseRepository.save(aPurchase);
    }

    @Override
    public Purchase findById(Long id) throws PurchaseDoesNotExistException {
        Purchase aPurchase = this.purchaseRepository.findById(id)
                .orElseThrow(()-> new PurchaseDoesNotExistException("The purchase with id: " + id.toString() + " does not exist."));
        return aPurchase;
    }

    @Override
    public void deletePurchase(Long id) {
        Purchase purchase = null;
        try {
            purchase = this.findById(id);
        } catch (PurchaseDoesNotExistException e) {
            //e.printStackTrace();
        }
        this.purchaseRepository.delete(purchase);
    }

    @Override
    public Purchase updatePurchase(Purchase aPurchase) throws PurchaseDoesNotExistException {
        Purchase purchase =this.findById(aPurchase.getId());
        if(aPurchase.getId()== null || aPurchase.getId()<0 ){
            throw new PurchaseDoesNotExistException("The purchase with id: " + purchase.getId().toString() + " does not exist.");
        }
        return this.savePurchase(aPurchase);
    }
}
