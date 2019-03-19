package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerClientImpl implements SellerService {

    @Autowired
    private SellerRepository repository;

    @Override
    public Seller createOrUpdate(Seller seller) {
        return repository.saveAndFlush(seller);
    }

    @Override
    public Boolean delete(long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
