package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);

    Client updateClient(Client client);

    Client findById(Long id);

    void deleteClient(Long id);

    List<Client> findAll();
}
