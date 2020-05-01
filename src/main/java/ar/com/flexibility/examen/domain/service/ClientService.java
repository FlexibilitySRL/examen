package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);
    Client updateClient(Long id, Client client);
    boolean deleteClient(Long id);

    List<Client> retrieveClients();
    Client retrieveClientById(Long id);
}
