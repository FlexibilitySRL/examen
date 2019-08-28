package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Client;

public interface ClientService {
	
	/**
	 * Crear un cliente
	 * @param cliente a crear
	 * @return cliente creado
	 */
	public Client create(Client client);

	/**
	 * Actualiza un cliente
	 * @param cliente a actualizar
	 * @return Client actualizado
	 */
	public Client update(Client client);

	/**
	 * Retorna un cliente en base al id
	 * @param id
	 * @return Client
	 */
	public Client findById(Long id);

	/**
	 * Retorna todos los cliente
	 * @return List<Client>
	 */
	public List<Client> findAll();

	/**
	 * Borra un cliente en base al id
	 * @param id
	 */
	public void deleteById(Long id);

}
