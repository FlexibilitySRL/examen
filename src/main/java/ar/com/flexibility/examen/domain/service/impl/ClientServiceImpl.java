package ar.com.flexibility.examen.domain.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ar.com.flexibility.examen.domain.model.db.Client;
import ar.com.flexibility.examen.domain.model.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.exception.ClientException;
import ar.com.flexibility.examen.exception.ProductException;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client createClient(Client client) {
		return clientRepository.saveAndFlush(client);
	}

	@Override
	public Client updateClient(Client client) {
		getClienteById(client.getId());
		return clientRepository.saveAndFlush(client);
	}

	@Override
	@Transactional
	public void deleteClient(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new ProductException("El identificador esta vacío");
		}
		Long identifier = Long.valueOf(id);
		Client client = getClienteById(identifier);
		client.setState(false);
		clientRepository.saveAndFlush(client);
	}

	@Override
	public Client getClienteById(Long id) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if (optionalClient.isPresent()) {
			return optionalClient.get();
		}
		throw new ClientException("No existe cliente");
	}

	public Client getClienteBydDocumentNumber(String documentNumber) {
		Optional<Client> optionalClient = clientRepository.findByDocumentNumber(documentNumber);
		if (optionalClient.isPresent()) {
			return optionalClient.get();
		}
		throw new ClientException("Existe un cliente con el numero de documento: " + documentNumber);
	}
}
