package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Client createClient (Client client) {
        return clientRepository.save(client);
    }


    @Override
    public void updateClient(Client client) throws ClientNotFoundException {

        if (!clientRepository.exists (client.getId ()))
            throw new ClientNotFoundException();

        clientRepository.save(client);
    }


    @Override
    public Client getClientById (Long id) throws ClientNotFoundException {
        Client client = clientRepository.findOne (id);

        if (client == null)
            throw new ClientNotFoundException();

        return client;
    }


    @Override
    public void deleteClient (Long id) throws ClientNotFoundException {
        try {
            clientRepository.delete(id);
        }catch (RuntimeException re) {
            throw new ClientNotFoundException();
        }
    }

}
