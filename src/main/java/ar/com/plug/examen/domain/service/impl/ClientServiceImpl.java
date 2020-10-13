package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.constants.PaymentsConstants;
import ar.com.plug.examen.domain.enums.PurchaseStatus;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.dao.IClientDao;
import ar.com.plug.examen.domain.service.IClientService;
import ar.com.plug.examen.domain.service.IPurchaseService;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;
import ar.com.plug.examen.exception.BusinessException;


@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	protected MessageSourceProvider messageSourceProvider;
	
	@Autowired
	private IPurchaseService purchaseService;
	
	@Autowired
	private IClientDao clientDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDAO.findAll();
	}

	@Override
	@Transactional
	public Client save(Client client) {
		return clientDAO.save(client);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		Client client = clientDAO.findById(id).orElse(null);
		if (client == null) {
			throw new BusinessException(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.CLIENT_ENTITY, id.toString()}));
		}
		return client;
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Client client = this.findById(id);
		this.validateClientDelete(client);
		clientDAO.delete(client);
	}

	private void validateClientDelete(Client client) {
		List<Purchase> pendingPurchases = purchaseService.findByClientAndStatus(client, PurchaseStatus.PENDING);
		if (pendingPurchases != null && pendingPurchases.size() > 0) {
			throw new BusinessException(messageSourceProvider.get(BusinessExceptionConstants.CANT_DELETE_CLIENT_PENDING_PURCHASES));
		}
	}
	
}
