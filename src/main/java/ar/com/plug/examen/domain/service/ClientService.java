package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> getClients();

    Client save(Client client);

    Optional<Client> findById(long id);

    void deleteById(long id);
}