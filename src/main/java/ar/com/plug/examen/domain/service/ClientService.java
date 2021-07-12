package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import java.util.List;

public interface ClientService {

  /**
   * Get all clients
   * @return List<ClientApi>
   */
  List<ClientApi> findAll();

  /**
   * Get client by id
   * @param id
   * @return ClientApi
   * @throws GenericNotFoundException
   */
  ClientApi findByIdChecked(Long id) throws GenericNotFoundException;

  /**
   * Save a client
   * @param clientApi
   * @return ClientApi
   * @throws GenericBadRequestException
   */
  ClientApi save(ClientApi clientApi) throws GenericBadRequestException;

  /**
   * Update a client
   * @param clientApi
   * @return ClientApi
   * @throws GenericNotFoundException
   * @throws GenericBadRequestException
   */
  ClientApi update(ClientApi clientApi) throws GenericNotFoundException, GenericBadRequestException;

  /**
   * Delete a client by id
   * @param id
   * @throws GenericNotFoundException
   */
  void delete(Long id) throws GenericNotFoundException;

}
