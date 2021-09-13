package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.dto.ClienteCompraRestDto;
import ar.com.plug.examen.domain.model.dto.ClienteRestDto;

public interface IClienteService {

	 public ClienteRestDto updateCliente(ClienteRestDto clienteRestDto);
	 
	 public List<ClienteRestDto> getClientes();

	 public ClienteRestDto getClienteById(Long idCliente);

	 public Boolean removeClienteById(Long id);

	 public ClienteCompraRestDto getClienteComprasById(Long idCliente);



}
