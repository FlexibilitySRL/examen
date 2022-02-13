package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getClients() {
        log.info("getting clients ...");
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        clientRepository.deleteById(id);
    }
}
