package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.dto.SellerDto;


public interface SellerService {
    
    /**
   * List all sellers
   * @return List<ClientApi>
   */
  List<SellerDto> findAll();

  /**
   * Find seller by id
   * @param id
   * @return SellerApi
   */
  SellerDto findByIdChecked(Long id);

  /**
   * Save a seller
   * @param sellerApi
   * @return SellerApi
   */
  SellerDto save(SellerDto sellerApi);

  /**
   * Update a seller
   * @param sellerApi
   * @return SellerApi
   */
  SellerDto update(SellerDto sellerApi);

  /**
   * Delete a seller by id
   * @param id
   */
  void delete(Long id);
    
}
