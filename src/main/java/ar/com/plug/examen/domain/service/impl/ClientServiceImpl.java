package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.mapper.Mapper;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ServiceGeneric;
import ar.com.plug.examen.domain.validator.ClientIdValidator;
import ar.com.plug.examen.domain.validator.ClientNameValidator;
import ar.com.plug.examen.domain.validator.DocumentValidator;
import ar.com.plug.examen.domain.validator.ListValidator;
/**
 * Este servicio provee un CRUD de clientes, para esto utiliza un repository que impacta los datos en la
 * base de datos.Antes de impactar en la base todos los objetos de entrada son validados, en caso de no pasar la validacion
 * se arrojara una excepcion
 * @author oscar
 *
 */
@Service
public class ClientServiceImpl implements ServiceGeneric<ClientApi,Client>{

	@Autowired
	private ClientRepository clientRepository;
	
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	@Override
	public void create(ClientApi entity) throws ValidatorException {
		ListValidator listValidator = new ListValidator(Arrays.asList(new ClientNameValidator(),
																	  new DocumentValidator()));
		listValidator.validate(entity);
		Client client = Mapper.mapperToClient(entity);
		client.setCreateDate(LocalDateTime.now());
		clientRepository.save(client);
	}

	@Override
	public void update(ClientApi entity) throws ValidatorException, NotExistException {
		ListValidator listValidator = new ListValidator(Arrays.asList(new ClientIdValidator(),
																	  new ClientNameValidator(),
																	  new DocumentValidator()));
		listValidator.validate(entity);
		Client client = clientRepository.save(Mapper.mapperToClient(entity));
		if(client == null)
			throw new NotExistException("El Cliente que quiere modificar no existe");

	}

	@Override
	public void delete(Long id) throws NotExistException, ValidatorException {
		ListValidator listValidator = new ListValidator(new ClientIdValidator());
		listValidator.validate(new ClientApi(id));
		Optional<Client> optional = clientRepository.findById(id);
		
		if(optional.isPresent()) {
			Client client = optional.get();
			client.setRemoved(true);
			clientRepository.save(client);
		}else
			throw new NotExistException("El Cliente que quiere eliminar no existe");
	}

	@Override
	public ClientApi find(Long id) throws ValidatorException, NotExistException {
		ListValidator listValidator = new ListValidator(new ClientIdValidator());
		listValidator.validate(new ClientApi(id));

		Optional<Client> optional = clientRepository.findById(id);
		
		if(!optional.isPresent() || optional.get().isRemoved())
			throw new NotExistException("El cliente que busca no existe");
		
		return Mapper.mapperToClientApi(optional.get());
	}
	@Override
	public List<ClientApi> findAll() throws NotExistException {
		List<Client> clients = clientRepository.findAll();
		List<ClientApi> clientsApi = new ArrayList<>(); 
		
		if(clients == null || clients.isEmpty())
			throw new NotExistException("No hay clientes cargados");

		clients.stream().filter(p-> !p.isRemoved()).forEach(r -> clientsApi.add(Mapper.mapperToClientApi(r)));
		
		return clientsApi;
	}


}
