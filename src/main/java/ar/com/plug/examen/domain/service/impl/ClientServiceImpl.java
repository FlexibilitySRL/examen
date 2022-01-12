package ar.com.plug.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repository;


	public Client findById(Long id) throws ResourceNotFoundException, BadResourceException {
		if (id == null) {
			throw new BadResourceException("Client is null or empty");
		}
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find Client with id: " + id));

	}

	public Page<Client> findAll(int pageNumber, int rowPerPage) {
		return repository.findAll(PageRequest.of(pageNumber, rowPerPage));
	}

	public Client save(Client client) throws BadResourceException {
		
		try {
			return repository.save(client);
		} catch (Exception e) {
			throw new BadResourceException("Client is not valid", e);
		}
		
	}

	public Client update(Client client) throws ResourceNotFoundException {
		findById(client.getId());
		return repository.save(client);
	}

	public void deleteById(Long id) throws ResourceNotFoundException, BadResourceException {
		findById(id);
		repository.deleteById(id);
	}


}
