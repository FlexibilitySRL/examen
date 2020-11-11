package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exceptions.ClientDoesNotExistException;
import ar.com.plug.examen.domain.model.Client;

import java.util.List;

public interface IClientService {

    List<Client> findAll();
    Client saveClient(Client aClient);
    Client findById(Long id) throws ClientDoesNotExistException;
    Client updateClient(Client aClient) throws ClientDoesNotExistException;
    void deleteClient(Long id) throws ClientDoesNotExistException;
}
