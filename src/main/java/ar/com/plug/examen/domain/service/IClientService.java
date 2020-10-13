package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Client;

public interface IClientService {

	public List<Client> findAll();
	
	public Client save(Client cliente);
	
	public Client findById(Long id);
	
	public void delete(Long id);
	
}
