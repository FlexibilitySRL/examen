package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.domain.converter.ClientConverter;
import ar.com.plug.examen.domain.exception.ClientException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClientServiceImpl implements ClientService
{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientConverter clientConverter;

    @Override
    public ClientDTO save(ClientDTO clientDTO)
    {
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
    public ClientDTO getClientByDocumentId(String documentId)
    {
        Optional<Client> client = clientRepository.findByDocumentId(documentId);

        if (client.isEmpty()) {
            throw new ClientException("Client with Document Id "+documentId+" not found");
        }

        return clientConverter.toDTO(client.get());
    }

}
