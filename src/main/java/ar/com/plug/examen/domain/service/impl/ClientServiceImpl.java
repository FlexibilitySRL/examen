package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.Messages;
import ar.com.plug.examen.domain.service.ValidatorsService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	
	private final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	private final static String ENTITY = "Client";

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorsService validators;
	
	/**
	 * @return The complete list of existent clients
	 */
	@Override
	public List<ClientApi> listAll() {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		List<ClientApi> result = converter.convertList(clientRepository.findAll(), ClientApi.class);
		logger.info(Messages.MSG_FOUND);
		return result;
	}

	/**
	 * Searches a client by its id
	 * @return A specific client
	 * @throws NotFoundException 
	 */
	@Override
	public ClientApi findById(long id) throws NotFoundException {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		ClientApi result = converter.convert(clientRepository.findOneById(id), ClientApi.class);
		if (Objects.isNull(result))
			throw NotFoundException.unableToFindException(ENTITY);
		logger.info(Messages.MSG_FOUND);
		return result;
	}

	/**
	 * Searches a client by its name
	 * Search is case insensitive and matches partially
	 * @return The list of clients which names matches a given string
	 */
	@Override
	public List<ClientApi> findByName(String name) {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		List<ClientApi> result = converter.convertList(clientRepository.findByName(name), ClientApi.class);
		logger.info(Messages.MSG_FOUND);
		return result;
	}
	
	/**
	 * Persists a new client
	 * @return Saved client
	 */
	@Override
	public ClientApi save(ClientApi client) throws BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(client, true);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

		Client persisted = clientRepository.save(converter.convert(client, Client.class));
		logger.info(String.format(Messages.MSG_SUCCESSFULLY_CREATED, ENTITY, persisted.getId()));

		return converter.convert(persisted, ClientApi.class);
	}

	/**
	 * Removes an existing client by its id
	 */
    @Override
    public void deleteById(long id) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(id, false);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);

    	logger.info(String.format(Messages.MSG_PREPARING_DELETION, ENTITY));
    	if (!clientRepository.existsById(id)) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
    	clientRepository.deleteById(id);
    	logger.info(String.format(Messages.MSG_SUCCESSFULLY_DELETED, ENTITY)); 
    }

    /**
	 * Searches an existing client by its id and updates it
	 * @return The updated client
	 */
	@Override
	public ClientApi update(ClientApi client) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.checkCompleteObject(client, false);
		logger.info(Messages.MSG_VALIDATION_SUCCESSFUL);
		
		logger.info(String.format(Messages.MSG_PREPARING_UPDATE, ENTITY));
		if (!clientRepository.existsById(client.getId())) {
    		NotFoundException.unableToFindException(ENTITY);
    	}
		Client updated = clientRepository.save(converter.convert(client, Client.class));
		logger.info(String.format(Messages.MSG_SUCCESSFULLY_UPDATED, ENTITY, updated.getId()));
		return converter.convert(updated, ClientApi.class);
	}
   
}