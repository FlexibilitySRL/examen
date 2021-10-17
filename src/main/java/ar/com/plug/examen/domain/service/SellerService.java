package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {

    Seller save(Seller seller);
    Seller update(Seller seller);
    void delete(long sellerId);
    Optional<Seller> findById(long sellerId);
    Optional<List<Seller>> getAllActive();
    List<Seller> getAll();
}
