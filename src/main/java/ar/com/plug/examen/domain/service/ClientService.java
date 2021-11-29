package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.dto.ClientDto;


public interface ClientService {

    /**
     * List all clients
     *
     * @return List<ClientApi>
     */
    List<ClientDto> findAll();

    /**
     * Find client by id
     *
     * @param id
     * @return ClientApi
     */
    ClientDto findById(Long id);

    /**
     * Save a client
     *
     * @param clientApi
     * @return ClientApi
     */
    ClientDto save(ClientDto clientApi);

    /**
   * Update a client
   * @param clientApi
   * @return ClientApi
   */
    ClientDto update(ClientDto clientApi);

    /**
   * Delete a client by id
   * @param id
   */
    void delete(Long id);

}
