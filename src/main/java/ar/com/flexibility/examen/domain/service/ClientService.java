package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.db.Client;

public interface ClientService {

	/**
	 * Metodo que permite crear un cliente
	 * 
	 * @param client
	 * @return
	 */
	public Client createClient(Client client);

	/**
	 * Metodo que permite actulizar informacion de un cliente
	 * 
	 * @param client
	 * @return
	 */
	public Client updateClient(Client client);

	/**
	 * Metodo que permite eliminar informacion de un cliente
	 * 
	 * @param id
	 */
	public void deleteClient(String id);

	/**
	 * Metodo que permite consultar por id la información de un cliente
	 * 
	 * @param id
	 * @return
	 */
	Client getClienteById(Long id);

}
