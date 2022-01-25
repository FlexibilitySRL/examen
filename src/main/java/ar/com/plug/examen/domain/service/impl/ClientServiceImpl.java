package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.domain.mapper.ClientMapper;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientServiceImpl implements ClientService
{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDTO save(ClientDTO clientDTO)
    {
        Client client = clientRepository.save(clientMapper.clientDTOToClient(clientDTO));
        return clientMapper.clientToClientDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClients()
    {
        return clientRepository.findAll()
              .stream()
              .map(clientMapper::clientToClientDTO)
              .collect(Collectors.toList());
    }
}
