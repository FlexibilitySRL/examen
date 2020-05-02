package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Seller;

import java.util.List;

public interface SellerService {

    Seller addSeller(Seller seller);
    Seller updateSeller(Long id, Seller seller);
    boolean deleteSeller(Long id);

    List<Seller> retrieveSellers();
    Seller retrieveSellerById(Long id);
}
