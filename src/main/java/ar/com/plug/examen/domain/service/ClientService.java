package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientDTO;
import java.util.List;

/**
 * Service to save, update, delete and get clients
 */
public interface ClientService
{
    /**
     * Save a client by the given clientDTO
     *
     * @param clientDTO
     * @return ClientDTO
     */
    ClientDTO save(ClientDTO clientDTO);

    /**
     * Get all clients
     *
     * @return List<ClientDTO>
     */
    List<ClientDTO> getAllClients();

    /**
     * Gets client by id
     *
     * @param id
     * @return ClientDTO
     */
    ClientDTO getClientById(Long id);

    /**
     * Update a client given a clientDTO
     *
     * @param clientDTO
     * @return ClientDTO
     */
    ClientDTO update(ClientDTO clientDTO);

    /**
     * Delete a client by Id
     *
     * @param id
     */
    void delete(Long id);


}
