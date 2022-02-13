package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client save(Client client);

    Client findById(long id);

    void deleteById(long id);
}