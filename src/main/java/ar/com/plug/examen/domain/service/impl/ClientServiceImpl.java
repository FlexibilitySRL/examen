package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import ar.com.plug.examen.domain.Exeptions.NotFoundException;
import ar.com.plug.examen.domain.Repository.ClientRepository;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.ValidatorService;

@Service
public class ClientServiceImpl implements ClientService{
	
	private final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	private final static String ENTITY = "Client";

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorService validators;

	/**
	 * The complete list of existent clients
	 * @return List<ClientApi>
	 */
	@Override
	@Transactional
	public List<ClientApi> listAll() {
		logger.info("Searching requested data...");
		List<ClientApi> result = converter.convertList(clientRepository.findAll(), ClientApi.class);
		logger.info("Found!");
		return result;
	}

	/**
	 * Searches a client by its id
	 * @param long id
	 * @return ClientApi client
	 */
	@Override
	@Transactional
	public ClientApi findById(long id) throws NotFoundException {
		try {
			logger.info("Searching requested data...");
			ClientApi result = converter.convert(clientRepository.findById(id).get(), ClientApi.class);
			logger.info("Founded!");
			return result;
		} catch (NoSuchElementException iae) {
			throw NotFoundException.unableToFindException(ENTITY);
		}
	}

	/**
	 * Searches a client by its name
	 * Search is case insensitive and matches partially
	 * Returns the list of clients which names matches a given string
	 * @param String name
	 * @return List<ClientApi>
	 */
	@Override
	@Transactional
	public List<ClientApi> findByName(String name) {
		logger.info("Searching requested data");
		List<ClientApi> result = converter.convertList(clientRepository.findByName(name), ClientApi.class);
		logger.info("Founded!");
		return result;
	}

	/**
	 * Persists a new client and returns it
	 * @param ClientApi client
	 * @return ClientApi client
	 */
	@Override
	@Transactional
	public ClientApi save(ClientApi client) throws BadRequestException {
		logger.info("Validating provided data");
		validators.checkCompleteObject(client, true);
		logger.info("Validation successful");

		Client persisted = clientRepository.save(converter.convert(client, Client.class));
		logger.info(String.format("%1$s successfully created with id: %2$s!", ENTITY, persisted.getId()));

		return converter.convert(persisted, ClientApi.class);
	}

	/**
	 * Removes an existing client by its id
	 * @param id
	 * @return void
	 */
    @Override
    @Transactional
    public void deleteById(long id) throws NotFoundException {
    	logger.info(String.format("Preparing for deletion of a %1$s ...", ENTITY));
    	if (!clientRepository.existsById(id)) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
    	clientRepository.deleteById(id);
    	logger.info(String.format("%1$s successfully deleted!", ENTITY)); 
    }

    /**
	 * Searches an existing client by its id and updates it, returns the updated client
	 * @param ClientApi client
	 * @return ClientApi updatedClient
	 */
	@Override
	@Transactional
	public ClientApi update(ClientApi client) throws NotFoundException, BadRequestException {
		logger.info("Validating provided data...");
		validators.checkCompleteObject(client, false);
		logger.info("Validation successful!");

		logger.info(String.format("Preparing for update of a %1$s ...", ENTITY));
		if (!clientRepository.existsById(client.getId())) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
		Client updated = clientRepository.save(converter.convert(client, Client.class));
		logger.info(String.format("%1$s with id: %2$s - successfully updated!", ENTITY, updated.getId()));
		return converter.convert(updated, ClientApi.class);
	}
}
