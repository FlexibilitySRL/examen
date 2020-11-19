package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ConverterService converter;

	@Autowired
	ValidatorsService validators;
	
	@Override
	public List<ClientApi> listAll() {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		return converter.convertList(clientRepository.findAll(), ClientApi.class);
	}

	@Override
	public ClientApi findById(Long id) throws NotFoundException {
		try {
			logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
			return converter.convert(clientRepository.findById(id).get(), ClientApi.class);
		} catch (NoSuchElementException | IllegalArgumentException nse) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, "Client");
			logger.error(errorMsg);
            throw new NotFoundException(errorMsg);
		}
	}

	@Override
	public List<ClientApi> findByName(String name) throws NotFoundException {
		logger.info(Messages.MSG_SEARCHING_REQUESTED_DATA);
		return converter.convertList(clientRepository.findByName(name), ClientApi.class);
	}
	
	@Override
	public ClientApi save(ClientApi client) throws BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.validateClient(client, false);

		Client persisted = clientRepository.save(converter.convert(client, Client.class));
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_CREATED, "Client", persisted.getId()); 
		logger.info(successMsg);

		return converter.convert(persisted, ClientApi.class);
	}


    @Override
    public void deleteById(Long id) throws NotFoundException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
    	this.validateExistence(id);
    	
        clientRepository.deleteById(id);
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_DELETED, "Client"); 
		logger.info(successMsg);
    }

	@Override
	public ClientApi update(ClientApi client) throws NotFoundException, BadRequestException {
		logger.info(Messages.MSG_VALIDATING_PROVIDED_DATA);
		validators.validateClient(client, true);
		this.validateExistence(client.getId());

		Client updated = clientRepository.save(converter.convert(client, Client.class));
		String successMsg = String.format(Messages.MSG_SUCCESSFULLY_UPDATED, "Client", updated.getId()); 
		logger.info(successMsg);

		return converter.convert(updated, ClientApi.class);
	}
    
	/** VALIDATORS **/
	private void validateExistence(Long id) throws NotFoundException {
		/** Validates the existence of the client to be deleted **/
        if (!clientRepository.findById(id).isPresent()) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, "Client");
			logger.error(errorMsg);
            throw new NotFoundException(errorMsg);
        }
    }
}