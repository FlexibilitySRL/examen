package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;

import java.util.List;

/**
 *  Client Service contract. It exposes the methods that should be
 *  implemented by the more specific classes.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
public interface ClientService {

    Client addClient(Client client);
    Client updateClient(Long id, Client client);
    boolean deleteClient(Long id);

    List<Client> retrieveClients();
    Client retrieveClientById(Long id);
}
