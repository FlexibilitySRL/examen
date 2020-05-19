package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.exception.EntityNotDeletedException;
import ar.com.flexibility.examen.exception.EntityNotFoundException;
import ar.com.flexibility.examen.exception.EntityNotSavedException;
import ar.com.flexibility.examen.exception.EntityNotUpdatedException;
import ar.com.flexibility.examen.exception.GenericException;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client create(Client client) throws GenericException {
		logger.info("Creating the client {}", client.toString());

		Client newClient = clientRepository.save(client);

		if (newClient == null) {
			String msg = String.format("Error during creating the client %s", client);
			logger.error(msg);
			throw new EntityNotSavedException(msg);
		}

		logger.info("Client {} successfully created", newClient.getId());
		return newClient;
	}

	@Override
	public Client update(Client client) throws GenericException {
		logger.info("Updating the client {}", client.toString());

		if (!clientRepository.existsById(client.getId())) {

			String msg = String.format("Error retrieving the client %s", client.getId());
			logger.error(msg);
			throw new EntityNotFoundException(msg);
		}

		Client updatedClient = clientRepository.save(client);

		if (updatedClient == null) {
			String msg = String.format("Error during updating the client %s", client);
			logger.error(msg);
			throw new EntityNotUpdatedException(msg);
		}

		logger.info("Client {} successfully updated", client.getId());
		return updatedClient;
	}

	@Override
	public void delete(Long id) throws GenericException {
		logger.info("Deleging the client {}", id);

		if (!clientRepository.existsById(id)) {
			String msg = String.format("Can't find the client %s", id);
			logger.error(msg);
			throw new EntityNotDeletedException(msg);
		}

		try {
			clientRepository.deleteById(id);
			logger.info("Client {} successfully deleted", id);
		} catch (Exception e) {
			String msg = String.format("Error during deleting the client %s \n %s", id, e.getMessage());
			logger.error(msg);
			throw new EntityNotDeletedException(msg);
		}
	}

	@Override
	public List<Client> getAll() {
		logger.info("Retrieving all clients");
		List<Client> result = (List<Client>) clientRepository.findAll();
		logger.info("All clients result: " + result.toString());
		return result;
	}

	@Override
	public Client getById(Long id) throws GenericException {
		logger.info("Retrieving client {}", id);

		Optional<Client> client = clientRepository.findById(id);
		if (!client.isPresent()) {
			String msg = String.format("Error retrieving the client %s", id);
			logger.error(msg);
			throw new EntityNotFoundException(msg);
		}

		logger.info("Returning client: " + client.get().toString());
		return client.get();
	}
}
