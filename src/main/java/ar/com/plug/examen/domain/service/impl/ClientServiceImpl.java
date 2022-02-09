package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.domain.converter.ClientConverter;
import ar.com.plug.examen.domain.exception.ClientFoundException;
import ar.com.plug.examen.domain.exception.ClientNotFoundException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Implementation of {@link ClientService}
 */
@Service
public class ClientServiceImpl implements ClientService
{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientConverter clientConverter;

    @Override
    @Transactional
    public ClientDTO save(ClientDTO clientDTO)
    {
        Optional<Client> client1 = clientRepository
              .findByDocumentIdAndDocumentType(clientDTO.getDocumentId(), clientDTO.getDocumentType());

        if (client1.isPresent()) {
            throw new ClientFoundException("The client is already registered");
        }

        Client client = clientRepository.save(clientConverter.toModel(clientDTO));
        return clientConverter.toDTO(client);
    }


    @Override
    public List<ClientDTO> getAllClients()
    {
        return clientRepository.findAll()
              .stream()
              .map(clientConverter::toDTO)
              .collect(Collectors.toList());
    }

    /**
     * Get client by documentId
     *
     * @param documentId
     * @return ClientDTO
     */
    @Override
    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long id)
    {
        return clientConverter.toDTO(getClientByIdIfExists(id));
    }

    @Override
    @Transactional
    public ClientDTO update(ClientDTO clientDTO)
    {
        getClientByIdIfExists(clientDTO.getId());
        return clientConverter.toDTO(clientRepository.save(clientConverter.toModel(clientDTO)));
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        clientRepository.delete(getClientByIdIfExists(id));
    }

    /**
     * Get Client by id, if exists return it. If not, throws exception.
     *
     * @param id
     * @return
     */
    private Client getClientByIdIfExists(Long id) {

        return clientRepository.findById(id)
              .orElseThrow(() -> new ClientNotFoundException("Client with Id "+id+" not found"));
    }
}
