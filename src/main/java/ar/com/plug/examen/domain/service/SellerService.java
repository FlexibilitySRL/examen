package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.SellerApi;
import java.util.List;


public interface SellerService {
    
    /**
   * List all sellers
   * @return List<ClientApi>
   */
  List<SellerApi> findAll();

  /**
   * Find seller by id
   * @param id
   * @return SellerApi
   */
  SellerApi findByIdChecked(Long id);

  /**
   * Save a seller
   * @param sellerApi
   * @return SellerApi
   */
  SellerApi save(SellerApi sellerApi);

  /**
   * Update a seller
   * @param sellerApi
   * @return SellerApi
   */
  SellerApi update(SellerApi sellerApi);

  /**
   * Delete a seller by id
   * @param id
   */
  void delete(Long id);
    
}
