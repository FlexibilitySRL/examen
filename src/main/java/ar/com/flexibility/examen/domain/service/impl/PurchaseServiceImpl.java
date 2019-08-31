package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repository.PurchaseItemRepository;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);
    private PurchaseRepository repo;
    private PurchaseItemRepository repoItem;

    public PurchaseServiceImpl(PurchaseRepository repo,
                               PurchaseItemRepository repoItem) {
        this.repo = repo;
        this.repoItem = repoItem;
    }

    @Override
    public Purchase findById(Long id) {
        log.debug("Buscando compra con id: {}", id);
        return repo.findOne(id);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Borrando compra: {}", id);
        repo.delete(id);
    }

    @Override
    public Purchase create(Purchase purchase) {
        log.debug("Grabando : {}", purchase.getItem());
        repoItem.save(purchase.getItem());
        log.debug("Grabando : {}", purchase);
        return repo.save(purchase);
    }

    @Override
    public Purchase update(Purchase purchase) {
        log.debug("Actualizando : {}", purchase);

        Purchase purchaseToUpdate = repo.findOne(purchase.getId());

        if (purchaseToUpdate == null){
            return null;
        }

        purchaseToUpdate.done();

        return repo.save(purchaseToUpdate);
    }

    @Override
    public List<Purchase> findAll() {
        log.debug("Obteniendo todas las compras");
        return repo.findAll();
    }
}
