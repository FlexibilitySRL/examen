package ar.com.plug.examen.domain.services;



import ar.com.plug.examen.domain.dtos.ClienteDTO;

import java.util.List;

public interface IClienteService {

    public List<ClienteDTO> getAll();
    public ClienteDTO save(ClienteDTO cliente);
    public void delete(ClienteDTO cliente);
    public List<ClienteDTO> getClienteByNombre(String nombre);
}
