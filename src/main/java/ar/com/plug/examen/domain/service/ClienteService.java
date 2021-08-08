package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.dto.ClienteAltaDto;
import ar.com.plug.examen.domain.dto.ClienteUpdateDto;
import ar.com.plug.examen.domain.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> getClients();
	
	public Optional<Cliente> findById(Integer id);

	public Cliente createClient(ClienteAltaDto d);
	
	public Cliente updateClient(ClienteUpdateDto dto);
	
	public void deleteClient(Integer id);

	
}
