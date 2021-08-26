package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Clientes;

public interface ClienteService {
	
	public ResponseDTO saveClientes(Clientes clientes);

	public ResponseDTO deleteClientes(Integer id);

	public ResponseDTO updateClientes(Clientes clientes);

}
