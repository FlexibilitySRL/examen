package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.ClientApi;

import java.util.List;

public interface ClientService {

    ClientApi create(ClientApi clientApi);

    ClientApi retrieve(Long id);

    List<ClientApi> list();

    void remove(Long id);

    ClientApi update(Long id, ClientApi clientApi);
}
