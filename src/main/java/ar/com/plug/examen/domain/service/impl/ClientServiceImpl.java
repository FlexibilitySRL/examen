package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.ClientModel;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author AGB
 */
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public ClientModel saveClient(ClientModel client) {
        return repository.save(client);
    }

    @Override
    public List<ClientModel> saveClients(List<ClientModel> clients) {
        return repository.saveAll(clients);
    }

    @Override
    public List<ClientModel> getClients() {
        return repository.findAll();
    }

    @Override
    public ClientModel getClientById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ClientModel getClientByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public String deleteClient(int id) {
        repository.deleteById(id);
        return ("Client Id: " + id + " Removed");
    }

    @Override
    public ClientModel updateClient(ClientModel client) {
        //Get existingProduct from db
        ClientModel existingClient = repository.findById(client.getId()).orElse(null);

        //Update audit model
        existingClient.setUpdateDt(client.getUpdateDt());
        existingClient.setUpdateBy(client.getUpdateBy());

        //Update class atributes
        existingClient.setName(client.getName());
        existingClient.setAge(client.getAge());
        existingClient.setEmail(client.getEmail());

        return repository.save(existingClient);
    }

}
