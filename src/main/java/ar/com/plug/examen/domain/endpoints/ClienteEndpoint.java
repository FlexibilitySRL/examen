package ar.com.plug.examen.domain.endpoints;

import ar.com.plug.examen.domain.dtos.ClienteDTO;

import java.util.List;

public interface ClienteEndpoint {

    String BASE = "/v1";
    String GET_ALL_CLIENTES = "/get-all-clientes";
    String ADD_CLIENTE = "/add-cliente";
    String DELETE_CLIENTE = "/delete-cliente";
    String GET_CLIENTES_BY_NOMBRE = "/get-clientes-by-nombre";

    public List<ClienteDTO> getAll();
    public ClienteDTO save(ClienteDTO cliente);
    public void delete(ClienteDTO cliente);
    public List<ClienteDTO> getClienteByNombre(String nombre);
}
