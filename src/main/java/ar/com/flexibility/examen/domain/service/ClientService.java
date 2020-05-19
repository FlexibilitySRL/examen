package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.exception.GenericException;

public interface ClientService {

	public Client create(Client client) throws GenericException ;

	public Client getById(Long id) throws GenericException;

	public List<Client> getAll() throws GenericException;

	public Client update(Client client) throws GenericException;

	public void delete(Long id) throws GenericException;

}
