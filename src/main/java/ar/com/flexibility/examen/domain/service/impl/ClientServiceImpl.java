package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.ConstantsProps;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

	// ---------------
	// Logger
	// ---------------
	private final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

	// ---------------
	// Attributes
	// ---------------
	@Autowired
	private ConstantsProps constants;
	@Autowired
	private MessagesProps messages;
	@Autowired
	private ClientRepository clientRepository;

	// ---------------
	// Methods
	// ---------------
	@Override
	@Transactional
	public void delete(String identifier) throws ServiceException {
		try {
			logger.info("delete client");
			if (this.clientRepository.deleteByIdentifier(identifier) != 1) {
				logger.warn("It was not possible to removes the client");
				throw new ServiceException(this.messages.getClientNotFound());
			}
			logger.info("delete client success");
		} catch (ServiceException e) { 
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public Client get(String identifier) throws ServiceException {
		try {
			logger.info("get client");
			
			Client entity = this.clientRepository.getFirstByIdentifier(identifier);
			
			if (Objects.isNull(entity)) {
				logger.warn("Client not found with the identifier");
				throw new ServiceException(this.messages.getClientNotFound());
			}
			
			this.cleanPurchases(entity);
			
			logger.info("get client success");
			return entity;
		} catch (ServiceException e) { 
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public List<Client> list() throws ServiceException {
		try {
			logger.info("list of clients");
			
			List<Client> data = this.clientRepository.findAll();
			data.stream().forEach( e -> this.cleanPurchases(e) );
			
			return data;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	@Transactional
	public void save(String identifier, String name, String surname) throws ServiceException {
		try {
			logger.info("save client");
			// Checks if a client already exists with the identifier
			if (existsClient(identifier)) {
				logger.warn("One client already exists with the identifier");
				throw new ServiceException(this.messages.getClientDuplicated());
			}

			Client entity = new Client();
			entity.setIdentifier(identifier);
			entity.setName(name);
			entity.setSurname(surname);

			this.clientRepository.save(entity);
			logger.info("save client success");
		} catch (ServiceException e) { 
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	@Transactional
	public void update(String identifier, String newIdentifier, String name, String surname) throws ServiceException {
		try {
			logger.info("update client");

			Client entity = this.clientRepository.getFirstByIdentifier(identifier);

			if (Objects.isNull(entity)) {
				logger.warn("Client not found with the current identifier");
				throw new ServiceException(this.messages.getClientNotFound());
			}

			if (!Strings.isNullOrEmpty(newIdentifier)) {
				if (!newIdentifier.equals(identifier) && existsClient(newIdentifier)) {
					logger.warn("One seller already exists with the new identifier");
					throw new ServiceException(this.messages.getClientDuplicated());
				}
				logger.info("update seller's identifier");
				entity.setIdentifier(newIdentifier);
			}
			entity.setName(name);
			entity.setSurname(surname);

			this.clientRepository.save(entity);
			logger.info("update client success");
		} catch (ServiceException e) { 
			throw e;
		} catch (Exception e) {
			logger.error(String.format(this.constants.getExceptionError(), e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}
	
	@Override
	public void cleanPurchases (Client entity) {
		if (Objects.nonNull(entity)) {
			entity.getPurchases().stream().forEach(
					s -> {
						s.setClient(null);
						s.setProduct(null);
						s.setSeller(null);
					});
		}
	}

	private boolean existsClient(String identifier) {
		return Objects.nonNull(this.clientRepository.getFirstByIdentifier(identifier));
	}

}
