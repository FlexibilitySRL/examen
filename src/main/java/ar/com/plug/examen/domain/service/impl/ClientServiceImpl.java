package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.exceptions.ResourceNotFoundError;
import ar.com.plug.examen.domain.mappers.ClientMapper;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.repositories.ClientRepository;
import ar.com.plug.examen.domain.service.IClientRepo;
import ar.com.plug.examen.domain.validators.Validator;
import ar.com.plug.examen.entities.Client;

@Service
public class ClientServiceImpl implements IClientRepo{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ClientMapper clientMapper;
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	
	@Override
	public ClientDTO findClientById(long id) throws ResourceNotFoundError {
		logger.info("Finding client with ID: " + id);
		ClientDTO c = this.clientMapper.clientEntityToClientDTO(this.clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundError("Client not found")
		));
		logger.info("Finding client DONE.");
		return  c;
	}
	
	@Override
	public List<ClientDTO> findAll() {
		logger.info("Finding all clients...");
		List<ClientDTO> clients = this.clientMapper.clientsToListClientDTO(this.clientRepository.findAll());
		logger.info("Finding all clients DONE.");
	    return clients;
	}

	@Override
	@Transactional
	public ClientDTO save(ClientDTO clientDTO) throws BadRequestError {
		logger.info("Validating client...");
		this.validator.validateClient(clientDTO);
		logger.info("Validating client DONE");
		logger.info("Saving client...");
		ClientDTO client = this.clientMapper.clientEntityToClientDTO(this.clientRepository.save(this.clientMapper.clientDTOToClientEntity(clientDTO)));
		logger.info("Saving client DONE");
		return client;
	}

	@Override
	@Transactional
	public void delete(long id) throws ResourceNotFoundError {
		logger.info("Finding client by ID: " + id);
		this.clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Client not found"));
		logger.info("Client with ID : " + id +" found");
		logger.info("Deleting client");
		this.clientRepository.deleteById(id);
		logger.info("Deleting client DONE");
	}

	@Override
	@Transactional
	public ClientDTO update(ClientDTO clientDTO) throws ResourceNotFoundError, BadRequestError {
		logger.info("Validating client...");
		this.validator.validateClient(clientDTO);
		logger.info("Validating client DONE");
		logger.info("Finding client by ID: " + clientDTO.getId());
		this.clientRepository.findById(clientDTO.getId()).orElseThrow(() -> new ResourceNotFoundError("Client not found"));
		logger.info("Client found");
		logger.info("Saving client...");
		ClientDTO c = this.clientMapper.clientEntityToClientDTO(this.clientRepository.save(this.clientMapper.clientDTOToClientEntity(clientDTO))); 
		logger.info("Saving client DONE");
		return c;
	}


}
