package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.build.ClientResponseBuilder;
import ar.com.flexibility.examen.app.api.response.ClientApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;

import java.util.ArrayList;
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
	// Constants
	// ---------------
	private static final String EXCEPTION = "ClientServiceImpl exception: %s";

	// ---------------
	// Logger
	// ---------------
	private final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

	// ---------------
	// Attributes
	// ---------------
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
				throw new ServiceException(this.messages.getClientPurchasesError());
			}
			logger.info("delete client success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public Client getEntity(String identifier) throws ServiceException {
		try {
			logger.info("get client entity");

			Client entity = this.clientRepository.getFirstByIdentifier(identifier);

			if (Objects.isNull(entity)) {
				logger.warn("Client not found with the identifier");
				throw new ServiceException(this.messages.getClientNotFound());
			}

			logger.info("get client entity success");
			return entity;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public ClientApiResponse get(String identifier) throws ServiceException {
		try {
			logger.info("get client");
			Client entity = this.getEntity(identifier);

			logger.info("get client success");
			return this.mergeResponse(entity);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public List<ClientApiResponse> list() throws ServiceException {
		try {
			logger.info("list of clients");

			List<ClientApiResponse> response = new ArrayList<>();

			List<Client> data = this.clientRepository.findAll();
			if (Objects.nonNull(data))
				data.stream().forEach(e -> response.add(this.mergeResponse(e)));

			logger.info("list of clients success");
			return response;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
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
			logger.error(String.format(EXCEPTION, e.getMessage()));
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
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	private boolean existsClient(String identifier) {
		return Objects.nonNull(this.clientRepository.getFirstByIdentifier(identifier));
	}

	private ClientApiResponse mergeResponse(Client entity) {
		return ClientResponseBuilder.mergeResponse(entity);
	}

}
