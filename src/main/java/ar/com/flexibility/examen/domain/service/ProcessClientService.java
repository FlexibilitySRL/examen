package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Client;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
public interface ProcessClientService {
	
	/**
	 * This method return information about a client by its id.
	 * 
	 * @param id
	 * @return Clients.
	 */
	public Client getClientById(Long id);
	
	
	/**
	 * This method return all information about clients.
	 * 
	 * @param id
	 * @return List<Clients>.
	 */
	public List<Client> getClients();
	
	/**
	 * This method save a new client in database.
	 * 
	 * @return Boolean
	 */
	public Boolean saveClient(Client client);
	
	/**
	 * This method update a new client in database by id.
	 * 
	 * @return Boolean
	 */
	public Boolean updateClient(Client client, Long id);
	
	/**
	 * This method delete a new client in database by id.
	 * 
	 * @return Boolean
	 */
	public Boolean deleteClient(Long id);
}
