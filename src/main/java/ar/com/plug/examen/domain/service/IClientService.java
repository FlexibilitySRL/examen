package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.dto.ClientDto;

public interface IClientService {

	public List<ClientDto> findAll();
	
	public ClientDto findById(Long id) throws Exception;
	
	public ClientDto save(ClientDto client);
	
	public void delete(Long id);

	public ClientDto update(ClientDto client) throws Exception;
}
