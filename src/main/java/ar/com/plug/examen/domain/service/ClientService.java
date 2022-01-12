package ar.com.plug.examen.domain.service;

import org.springframework.data.domain.Page;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Client;

public interface ClientService {

	Client findById(Long id) throws ResourceNotFoundException;

	Page<Client> findAll(int pageNumber, int rowPerPage);

	Client save(Client client) throws BadResourceException;

	Client update(Client client) throws ResourceNotFoundException;

	void deleteById(Long id) throws ResourceNotFoundException, BadResourceException;

}