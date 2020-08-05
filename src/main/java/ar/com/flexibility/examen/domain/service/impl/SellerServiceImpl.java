package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.build.SellerResponseBuilder;
import ar.com.flexibility.examen.app.api.response.SellerApiResponse;
import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service("sellerService")
public class SellerServiceImpl implements SellerService {

	// ---------------
	// Constants
	// ---------------
	private static final String EXCEPTION = "SellerServiceImpl exception: %s";

	// ---------------
	// Logger
	// ---------------
	private final Logger logger = LogManager.getLogger(SellerServiceImpl.class);

	// ---------------
	// Attributes
	// ---------------
	@Autowired
	private MessagesProps messages;
	@Autowired
	private SellerRepository sellerRepository;

	// ---------------
	// Methods
	// ---------------
	@Override
	@Transactional
	public void delete(String identifier) throws ServiceException {
		try {
			logger.info("delete seller");
			if (this.sellerRepository.deleteByIdentifier(identifier) != 1) {
				logger.warn("It was not possible to removes the seller");
				throw new ServiceException(this.messages.getSellerSalesError());
			}
			logger.info("delete seller success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public Seller getEntity(String identifier) throws ServiceException {
		try {
			logger.info("get seller entity");
			Seller entity = this.sellerRepository.getFirstByIdentifier(identifier);

			if (Objects.isNull(entity)) {
				logger.warn("Seller not found with the identifier");
				throw new ServiceException(this.messages.getSellerNotFound());
			}

			logger.info("get seller entity success");
			return entity;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public SellerApiResponse get(String identifier) throws ServiceException {
		try {
			logger.info("get seller");
			Seller entity = this.getEntity(identifier);

			logger.info("get seller success");
			return this.mergeResponse(entity);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	@Override
	public List<SellerApiResponse> list() throws ServiceException {
		try {
			logger.info("list of sellers");

			List<SellerApiResponse> response = new ArrayList<>();

			List<Seller> data = this.sellerRepository.findAll();
			if (Objects.nonNull(data))
				data.stream().forEach(e -> response.add(this.mergeResponse(e)));

			logger.info("list of sellers success");
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
			logger.info("save seller");
			// Checks if a seller already exists with the identifier
			if (existsSeller(identifier)) {
				logger.warn("One seller already exists with the identifier");
				throw new ServiceException(this.messages.getSellerDuplicated());
			}

			Seller entity = new Seller();
			entity.setIdentifier(identifier);
			entity.setName(name);
			entity.setSurname(surname);

			this.sellerRepository.save(entity);
			logger.info("save seller success");
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
			logger.info("update seller");

			Seller entity = this.sellerRepository.getFirstByIdentifier(identifier);

			if (Objects.isNull(entity)) {
				logger.warn("Seller not found with the current identifier");
				throw new ServiceException(this.messages.getSellerNotFound());
			}

			if (!Strings.isNullOrEmpty(newIdentifier)) {
				if (!newIdentifier.equals(identifier) && existsSeller(newIdentifier)) {
					logger.warn("One seller already exists with the new identifier");
					throw new ServiceException(this.messages.getSellerDuplicated());
				}
				logger.info("update seller's identifier");
				entity.setIdentifier(newIdentifier);
			}
			entity.setName(name);
			entity.setSurname(surname);

			this.sellerRepository.save(entity);
			logger.info("update seller success");
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(String.format(EXCEPTION, e.getMessage()));
			throw new ServiceException(this.messages.getServerError());
		}
	}

	private boolean existsSeller(String identifier) {
		return Objects.nonNull(this.sellerRepository.getFirstByIdentifier(identifier));
	}

	private SellerApiResponse mergeResponse(Seller entity) {
		return SellerResponseBuilder.mergeResponse(entity);
	}

}