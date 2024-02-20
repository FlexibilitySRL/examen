package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.Client;

public interface ClientService {
    Client findById(String id);

    List<Client> findAllByFilter(Client client);

    Client create(Client client);

    Client upDate(Client client);

    void remove(String id);
}
