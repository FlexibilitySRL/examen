package ar.com.plug.examen.domain.service.impl;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.ClientDto;
import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService
{
	private final ClientRepository clientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository)
	{
		this.clientRepository = clientRepository;
	}

	/**
	 * Returns a list of {@link Client} in a paginated format according to {@link PageDto} attributes.
	 * All the clients must have the active attribute set to true.
	 *
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with the active clients in it.
	 */
	@Override
	public PageDto<Client> getActiveClientsPageable(int pageNumber, int pageSize)
	{
		return new PageDto<>(
			this.clientRepository.findAllByActiveTrue(PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns the list of all {@link Client} in a paginated format according to {@link PageDto} attributes.
	 *
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with all clients in it.
	 */
	@Override
	public PageDto<Client> getAllClients(int pageNumber, int pageSize)
	{
		return new PageDto<>(
			this.clientRepository.findAll(PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns a {@link Client} according to its {@code id}.
	 *
	 * @param id id of the Client to query
	 * @return {@link Client} with the corresponding {@code id}.
	 */
	@Override
	public Client getClientById(Long id)
	{
		if(Objects.isNull(id)) {
			throw new NoSuchElementException("El id del cliente no puede ser nulo.");
		}
		Optional<Client> optionalClient = this.clientRepository.findById(id);
		if(optionalClient.isPresent()) {
			return optionalClient.get();
		} else {
			throw new NoSuchElementException("El id del cliente no se encuentra en la base de datos.");
		}
	}

	/**
	 * Returns a {@link Client} according to its {@code document} number.
	 *
	 * @param document legal document number of a client
	 * @return {@link Client} with the corresponding {@code sku}.
	 */
	@Override
	public Client getClientByDocumentNumber(String document)
	{
		if(StringUtils.isBlank(document)) {
			throw new NoSuchElementException("El número de documento del cliente no puede ser nulo.");
		}
		Client clientToReturn = this.clientRepository.findByDocument(document);
		if(Objects.nonNull(clientToReturn)) {
			return clientToReturn;
		} else {
			throw new NoSuchElementException("El número de documento del cliente no se encuentra en la base de datos.");
		}
	}

	/**
	 * Creates a new {@link Client} by using the data of the {@link ClientDto} passed as parameter.
	 *
	 * @param clientDto data which will be used to create a new Client.
	 * @return the new created {@link Client}
	 * @throws ValidationException    In case of a null parameters.
	 * @throws NoSuchElementException In case of that the client doesn't exist on the database.
	 */
	@Override
	public Client saveClient(ClientDto clientDto) throws ValidationException
	{
		if(Objects.isNull(clientDto)) {
			throw new ValidationException("Los datos para la creación de un cliente no pueden ser nulos.");
		}
		Client newClient = Client.builder()
			.name(clientDto.getName())
			.lastname(clientDto.getLastname())
			.document(clientDto.getDocument())
			.phone(clientDto.getPhone())
			.email(clientDto.getEmail())
			.active(clientDto.getActive())
			.modificationDate(new Date())
			.build();
		return this.clientRepository.save(newClient);
	}

	/**
	 * Updates a {@link Client} by using the data of the {@link ClientDto} passed as parameter.
	 *
	 * @param id        identifier to query the Client in the database.
	 * @param clientDto data which will be used to create a new Client.
	 * @return the updated {@link Client}
	 * @throws ValidationException    In case of a null parameters.
	 * @throws NoSuchElementException In case of that the client doesn't exist on the database.
	 */
	@Override
	public Client updateClient(Long id, ClientDto clientDto) throws ValidationException
	{
		if(Objects.isNull(id) || (Objects.isNull(clientDto))) {
			throw new ValidationException("Los datos para la actualización de un cliente no pueden ser nulos.");
		}
		Client clientFromDatabase =
			this.clientRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("El cliente con el id " + id + " no existe.")
			);
		clientFromDatabase.setName(clientDto.getName());
		clientFromDatabase.setLastname(clientDto.getLastname());
		clientFromDatabase.setDocument(clientDto.getDocument());
		clientFromDatabase.setPhone(clientDto.getPhone());
		clientFromDatabase.setEmail(clientDto.getEmail());
		clientFromDatabase.setActive(clientDto.getActive());
		clientFromDatabase.setModificationDate(new Date());
		return this.clientRepository.save(clientFromDatabase);
	}

	/**
	 * Updates a {@link Client} by setting the active attribute to false.
	 *
	 * @param id identifier to query the Client in the database.
	 * @return the inactivated {@link Client}
	 * @throws ValidationException    In case of a null parameters.
	 * @throws NoSuchElementException In case of that the client doesn't exist on the database.
	 */
	@Override
	public Client inactivateClient(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para la actualización de un cliente no pueden ser nulos.");
		}
		if(this.clientRepository.existsById(id)) {
			Client clientFromDatabase = this.clientRepository.getOne(id);
			clientFromDatabase.setActive(Boolean.FALSE);
			return this.clientRepository.save(clientFromDatabase);
		} else {
			throw new NoSuchElementException("El cliente con el id " + id + " no existe.");
		}
	}

	/**
	 * Performs a physical delete of a {@link Client}.
	 *
	 * @param id identifier to query the Client in the database.
	 * @return the inactivated {@link Client}
	 * @throws ValidationException    In case of a null parameters.
	 * @throws NoSuchElementException In case of that the client doesn't exist on the database.
	 */
	@Override
	public Long deleteClient(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para la actualización de un cliente no pueden ser nulos.");
		}
		if(this.clientRepository.existsById(id)) {
			this.clientRepository.deleteById(id);
			return id;
		} else {
			throw new NoSuchElementException("El cliente con el id " + id + " no existe.");
		}
	}
}
