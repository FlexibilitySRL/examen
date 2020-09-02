package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.ClientApi;

import java.util.List;

public interface ClientService {

	ClientApi create(ClientApi clientApi);

	ClientApi get(Long id);

    List<ClientApi> getClients();

    void delete(Long id);

    ClientApi update(Long id, ClientApi clientApi);
}