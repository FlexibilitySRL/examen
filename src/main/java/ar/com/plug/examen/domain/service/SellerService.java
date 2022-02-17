package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.SellerDTO;

public interface SellerService {
    void createSeller(SellerDTO sellerDTO);

    void deleteSeller(Long idSeller);

    void editSeller(Long idSeller, SellerDTO sellerDTO);
}
