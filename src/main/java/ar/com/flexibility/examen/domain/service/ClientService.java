package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;

public interface ClientService {

    Client createOrUpdate(Client client);

    Boolean delete(long id);
}
