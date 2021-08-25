package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.dto.ClienteDTO;

public interface ClienteService {	

	List<ClienteDTO> listarClientes();
	ClienteDTO consultarClientePorId(long id);
	ClienteDTO crearCliente(ClienteDTO clienteDTO);
	ClienteDTO actualizarCliente(ClienteDTO clienteDTO);
	void eliminarCliente(long id);
}
