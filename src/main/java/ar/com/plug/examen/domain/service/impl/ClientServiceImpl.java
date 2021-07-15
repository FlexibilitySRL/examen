package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	
	@Override
	public ClientDTO findClientById(long id) throws ResourceNotFoundError {
		return this.clientMapper.clientEntityToClientDTO(this.clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundError("Client not found")
		));
	}
	
	@Override
	public List<ClientDTO> findAll() {
	    return this.clientMapper.clientsToListClientDTO(this.clientRepository.findAll());
	}

	@Override
	@Transactional
	public ClientDTO save(ClientDTO clientDTO) throws BadRequestError {
		this.validator.validateClient(clientDTO);
		return this.clientMapper.clientEntityToClientDTO(this.clientRepository.save(this.clientMapper.clientDTOToClientEntity(clientDTO)));
	}

	@Override
	@Transactional
	public void delete(long id) throws ResourceNotFoundError {
		this.clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundError("Client not found"));
		this.clientRepository.deleteById(id);
	}

	@Override
	@Transactional
	public ClientDTO update(ClientDTO clientDTO) throws ResourceNotFoundError, BadRequestError {
		this.validator.validateClient(clientDTO);
		this.clientRepository.findById(clientDTO.getId()).orElseThrow(() -> new ResourceNotFoundError("Client not found"));
		return this.clientMapper.clientEntityToClientDTO(this.clientRepository.save(this.clientMapper.clientDTOToClientEntity(clientDTO)));
	}


}
