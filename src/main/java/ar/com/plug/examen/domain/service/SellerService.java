package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import java.util.List;

public interface SellerService {

  /**
   * Get all sellers
   * @return List<ClientApi>
   */
  List<SellerApi> findAll();

  /**
   * Get seller by id
   * @param id
   * @return SellerApi
   * @throws GenericNotFoundException
   */
  SellerApi findByIdChecked(Long id) throws GenericNotFoundException;

  /**
   * Save a seller
   * @param sellerApi
   * @return SellerApi
   * @throws GenericBadRequestException
   */
  SellerApi save(SellerApi sellerApi) throws GenericBadRequestException;

  /**
   * Update a seller
   * @param sellerApi
   * @return SellerApi
   * @throws GenericNotFoundException
   * @throws GenericBadRequestException
   */
  SellerApi update(SellerApi sellerApi) throws GenericNotFoundException, GenericBadRequestException;

  /**
   * Delete a seller by id
   * @param id
   * @throws GenericNotFoundException
   */
  void delete(Long id) throws GenericNotFoundException;

}
