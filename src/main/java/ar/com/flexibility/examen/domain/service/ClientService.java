package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.exception.ClientNotFoundException;

public interface ClientService {

    Client getClientById (Long id) throws ClientNotFoundException;
    Client createClient (Client client);
    void updateClient (Client client) throws ClientNotFoundException;
    void deleteClient (Long id) throws  ClientNotFoundException;

}
