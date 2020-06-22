package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.ClientApi;

import java.util.List;

public interface ClientService {
    /**
     * Retrieves a all the client in the database
     *
     * @return List<ClientApi>
     */
    List<ClientApi> all();

    /**
     * Retrieves one client from the database
     *
     * @param id identifying the client searched
     * @return ClientApi
     */
    ClientApi get(Long id);

    /**
     * Create a new client entry in the database
     *
     * @param clientApi with the values to be created
     * @return ClientApi
     */
    ClientApi create(ClientApi clientApi);

    /**
     * Updates an existing client in the database
     *
     * @param id        the id of the client to be updated
     * @param clientApi the new client information
     * @return ClientApi
     */
    ClientApi update(Long id, ClientApi clientApi);

    /**
     * Removes a client from the database
     *
     * @param id identifying the client to remove
     */
    void remove(Long id);


}
