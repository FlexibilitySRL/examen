package ar.com.plug.examen.domain.service.impl;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository  clientRepository;

    @Override
    public List<Client> listAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client createClient(Client client) {
        Optional<Client> clientDB = Optional.ofNullable(clientRepository.findByNumberID(client.getNumberID()));
        if(clientDB.isPresent()){
            log.error("Client already exits in database: {} ",  client.getFirstName());
            return clientDB.get();
        }
        try {
            Client clientCreated = clientRepository.save(client);
            log.info("Client created: {}", client.getFirstName());
            return clientCreated;
        } catch (DataIntegrityViolationException err){
            log.error("Error en la creacion de cliente: {}", err.getRootCause().toString());
            return null;
        }

    }

    @Override
    public Client updateClient(Client client) {
        Optional<Client> clientDB = Optional.ofNullable(getClient(client.getId()));
        if (clientDB.isEmpty()){
            log.error("Client not found: {}", client.getFirstName());
            return null;
        }
        Client clientUpdated = clientDB.get();
        clientUpdated.setFirstName(client.getFirstName());
        clientUpdated.setEmail(client.getEmail());
        clientUpdated.setLastName(client.getLastName());
        if(client.getPhotoUrl() != null) clientUpdated.setPhotoUrl(client.getPhotoUrl());
        log.info("client success updated: {}", client.getFirstName());
        return clientRepository.save(clientUpdated);
    }

    @Override
    public Client deleteClient(Long id) {
        Optional<Client> clientDB = Optional.ofNullable(getClient(id));
        if (clientDB.isEmpty()){
            log.error("Client not found");
            return null;
        }
        clientRepository.delete(clientDB.get());
        log.info("Client success deleted: {}", clientDB.get().getFirstName());
        return clientDB.get();
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }


}
