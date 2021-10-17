package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ISellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private ISellerRepository sellerRepository;

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller update(Seller seller) {
        return sellerRepository.update(seller);
    }

    @Override
    public void delete(long sellerId) {
      sellerRepository.delete(sellerId);
    }

    @Override
    public Optional<Seller> findById(long sellerId) {
        return sellerRepository.findById(sellerId);
    }

    @Override
    public Optional<List<Seller>> getAllActive() {
        return sellerRepository.getAllActive();
    }

    @Override
    public List<Seller> getAll() {
        return sellerRepository.getAll();
    }
}
