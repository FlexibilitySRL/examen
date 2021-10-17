package ar.com.plug.examen.domain.repository.impl;

import ar.com.plug.examen.domain.crud.SellerCrudRepository;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ISellerRepository;
import ar.com.plug.examen.utils.SellerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SellerRepository implements ISellerRepository {

    @Autowired
    private SellerCrudRepository sellerCrudRepository;

    @Override
    public Seller save(Seller seller) {

        return sellerCrudRepository.save(seller);
    }

    @Override
    public Seller update(Seller seller) {

        return sellerCrudRepository.save(seller);
    }

    @Override
    public void delete(long sellerId) {
       sellerCrudRepository.deleteById(sellerId);
    }

    @Override
    public Optional<Seller> findById(long sellerId) {

        return sellerCrudRepository.findById(sellerId);
    }

    @Override
    public Optional<List<Seller>> getAllActive() {
        return sellerCrudRepository.findByStateEquals(SellerUtils.ACTIVE);
    }

    @Override
    public List<Seller> getAll() {
        return (List<Seller>) sellerCrudRepository.findAll();
    }
}
