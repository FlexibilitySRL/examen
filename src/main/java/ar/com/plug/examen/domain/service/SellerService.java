package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.model.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {

    SellerDTO save(SellerDTO sellerDTO);
    SellerDTO update(SellerDTO sellerDTO);
    void delete(long sellerId);
    Optional<SellerDTO> findById(long sellerId);
    Optional<List<SellerDTO>> getAllActive();
    List<SellerDTO> getAll();
}
