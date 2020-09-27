package ar.com.plug.examen.domain.service.impl;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }


    @Override
    public Client updateClient(Client client) throws ClientNotFoundException {
        if (clientRepository.findById(client.getId()).orElse(null) == null)
            throw new ClientNotFoundException();

        return clientRepository.save(client);
    }


    @Override
    public Client getClient(Long id) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findById(id);

        if (!client.isPresent())
            throw new ClientNotFoundException();

        return client.get();
    }


    @Override
    public void deleteClient(Long id) throws ClientNotFoundException {
        try {
            clientRepository.deleteById(id);
        } catch (RuntimeException re) {
            throw new ClientNotFoundException();
        }
    }
}
