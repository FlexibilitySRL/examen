package ar.com.flexibility.examen.service;

import ar.com.flexibility.examen.app.dto.SellerDTO;
import ar.com.flexibility.examen.model.Seller;

public interface SellerService {

    void createSeller(SellerDTO sellerDTO);
    Seller retrieveSellerById(Long id);
    void updateSeller(Long id, SellerDTO sellerDTO);
    void deleteSellerById(Long id);
}
