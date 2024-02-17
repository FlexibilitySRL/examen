package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.domain.mapper.ClientMapper;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    /**
     * Adds a new client based on the provided client request.
     *
     * @param  clientApi   the client request to be added
     * @return                   the response containing the newly added client
     */
    @Override
    public ClientDto addClient(ClientApi clientApi) {
        if (clientApi == null) {
            log.error("ClientService :: addClient :: ClientRequest cannot be null");
            throw new IllegalArgumentException("ClientRequest cannot be null");
        }

        var newClient = ClientMapper.toClient(clientApi);
        newClient = clientRepository.save(newClient);
        log.info("ClientService :: addClient :: Client added {}", newClient);
        return ClientMapper.toClientDto(newClient);
    }

    @Override
    public List<ClientDto> findAll() {
        var clients = clientRepository.findAll();
        log.info("ClientService :: findAll :: FindAll {}", clients.size());
        return clients.stream()
                .map(ClientMapper::toClientDto)
                .collect(Collectors.toList());
    }

    /**
     * Find a client by ID.
     *
     * @param  id  the ID of the client to find
     * @return     the client DTO if found, otherwise null
     */
    @Override
    public ClientDto findClientById(Long id) {
        var client = clientRepository.findById(id).orElse(null);
        if (Objects.isNull(client)) {
            log.error("ClientService :: findClientById :: Client not found");
            return null;
        }
        log.info("ClientService :: findClientById :: Client found {}", client);
        return ClientMapper.toClientDto(client);
    }

    /**
     * Update a client with the given ID using the provided client request.
     *
     * @param  id            the ID of the client to be updated
     * @param  clientRequest the client request containing updated information
     * @return               the updated client DTO
     */
    @Override
    public ClientDto updateClient(Long id, ClientApi clientRequest) {
        var client = clientRepository.findById(id).orElse(null);
        if (Objects.isNull(client)) {
            log.error("ClientService :: updateClient :: Client not found");
            return null;
        }
        var clientUpdated = ClientMapper.updateClient(client, clientRequest);
        clientRepository.save(clientUpdated);
        log.info("ClientService :: updateClient :: Client was updated {}", clientUpdated);
        return ClientMapper.toClientDto(clientUpdated);
    }
    
    /**
     * Delete a client by ID.
     *
     * @param  id   the ID of the client to delete
     * @return      the deleted client DTO, or null if the client was not found
     */
    @Override
    public ClientDto deleteClient(Long id) {
        var client = clientRepository.findById(id).orElse(null);
        if (Objects.isNull(client)) {
            log.error("ClientService :: deleteClient :: Client not found");
            return null;
        }
        clientRepository.deleteById(id);
        log.info("ClientService :: deleteClient :: Client {} was deleted", id);
        return ClientMapper.toClientDto(client);
    }
}
