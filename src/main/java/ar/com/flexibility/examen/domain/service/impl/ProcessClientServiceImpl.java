package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dao.IClientDao;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.ProcessClientService;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@Service
@Qualifier("ProcessClientServiceImpl")
public class ProcessClientServiceImpl implements ProcessClientService {
	
	private final Logger LOG = LoggerFactory.getLogger(ProcessClientServiceImpl.class);

	@Autowired
	private IClientDao clientDao;
	
	@Override
	public Client getClientById(Long id) {
		Optional<Client> client = null;
		try {
			client = clientDao.findById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return client.get();
	}

	@Override
	public List<Client> getClients() {
		return (List<Client>) clientDao.findAll();
	}

	@Override	
	public Boolean saveClient(Client client) {
		Boolean resp = false;
		try {
			clientDao.save(client);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}

	@Override
	public Boolean updateClient(Client client, Long id) {
		Boolean resp = false;
		try {
			client.setId(id);
			clientDao.save(client);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}

	@Override
	public Boolean deleteClient(Long id) {
		Boolean resp = false;
		try {
			clientDao.deleteById(id);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}
}
