package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.entities.Client;

public interface IClientRepo {

	public ClientDTO findClientById(long id) throws ResourceNotFoundError;
	
	public List<ClientDTO> findAll();

	public ClientDTO save(ClientDTO client) throws BadRequestError;
	
	public ClientDTO update(ClientDTO client) throws ResourceNotFoundError, BadRequestError;

	public void delete(long id) throws ResourceNotFoundError;
	
	
}
