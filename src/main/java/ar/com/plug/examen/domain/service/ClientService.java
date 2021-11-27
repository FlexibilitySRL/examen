package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientApi;
import java.util.List;


public interface ClientService {

    /**
     * List all clients
     *
     * @return List<ClientApi>
     */
    List<ClientApi> findAll();

    /**
     * Find client by id
     *
     * @param id
     * @return ClientApi
     */
    ClientApi findById(Long id);

    /**
     * Save a client
     *
     * @param clientApi
     * @return ClientApi
     */
    ClientApi save(ClientApi clientApi);

    /**
   * Update a client
   * @param clientApi
   * @return ClientApi
   */
    ClientApi update(ClientApi clientApi);

    /**
   * Delete a client by id
   * @param id
   */
    void delete(Long id);

}
