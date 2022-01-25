package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientDTO;

import java.util.List;


public interface ClientService
{
    ClientDTO save(ClientDTO clientDTO);

    List<ClientDTO> getAllClients();
}
