package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.exception.ClientNotFoundException;

public interface ClientService {

    Client getClient (Long id) throws ClientNotFoundException;
    Client createClient (Client client);
    Client updateClient (Client client) throws ClientNotFoundException;
    void deleteClient (Long id) throws  ClientNotFoundException;

}
