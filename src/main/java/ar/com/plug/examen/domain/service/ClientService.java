package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto addClient(ClientApi clientApi);

    List<ClientDto> findAll();

    ClientDto findClientById(Long id);

    ClientDto updateClient(Long id, ClientApi clientApi);

    ClientDto deleteClient(Long id);
}
