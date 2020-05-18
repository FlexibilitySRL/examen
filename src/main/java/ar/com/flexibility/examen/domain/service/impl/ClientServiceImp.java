package ar.com.flexibility.examen.domain.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;

@Service
public class ClientServiceImp implements ClientService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImp.class);

	@Autowired
    ClientRepository clientRepository;

	@Override
	public ResponseEntity<?> getClients() {
		return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getClientById(Long id) {
		return new ResponseEntity<>(clientRepository.findById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> insertClient(Client client) {
		return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> updateClient(Long id, Client client) {
		client.setId(id);
		clientRepository.save(client);
		return new ResponseEntity<>("Cliente actualizado", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteClient(Long id) {
		try {
			clientRepository.delete(clientRepository.findById(id));
		} catch(InvalidDataAccessApiUsageException ex) {
	    	LOGGER.info(String.format("Cliente solicitado: %s, no encontrado", id));
	        return new ResponseEntity<>("cliente no encontrado", HttpStatus.NOT_FOUND);
	    }
		return  new ResponseEntity<>("Cliente borrado", HttpStatus.OK);
	}

}
