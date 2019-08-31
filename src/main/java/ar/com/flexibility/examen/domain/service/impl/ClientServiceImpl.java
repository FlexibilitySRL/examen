package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;


@Service
public class ClientServiceImpl implements ClientService {
	
	private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
	private final ClientRepository repo;
	
	
	public ClientServiceImpl(ClientRepository repo){
		this.repo = repo;
	}
	
	@Override
	public Client create(Client client) 
	{
		log.debug("Grabando : {}", client);
		return repo.save(client);
	}

	@Override
	public Client update(Client client) {
		log.debug("Actualizando : {}", client);

		Client clientToUpdate = repo.findOne(client.getId());

		if (clientToUpdate == null){
			return null;
		}

		clientToUpdate.setName(client.getName());
		clientToUpdate.setEmail(client.getEmail());

		return repo.save(clientToUpdate);
	}

	@Override
	public Client findById(Long id) {
		log.debug("Buscando cliente con id: {}", id);
        return repo.findOne(id);
	}

	@Override
	public List<Client> findAll() {
		log.debug("Obteniendo todos los clientes ");
        return repo.findAll();
	}

	@Override
	public void deleteById(Long id) {
		log.debug("Borrando cliente: {}", id);
        repo.delete(id);

	}

}
