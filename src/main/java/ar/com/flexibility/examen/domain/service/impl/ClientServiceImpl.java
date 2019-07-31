package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private Logger logger = LoggerFactory.getLogger("ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl");
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(Client client) {
        Client savedClient = clientRepository.save(client);

        checkServiceStatus(client, "The client was added succesfully.","An error ocurred while trying to add the client.");

        return savedClient;
    }

    private void checkServiceStatus(Client client, String infoMessage, String warningMessage) {
        if (client != null) {
            logger.info(infoMessage);
        } else {
            logger.warn(warningMessage);
        }
    }

    @Override
    public Client updateClient(Client client) {
        Client updatedClient = clientRepository.save(client);

        checkServiceStatus(updatedClient, "The client was updated succesfully.","An error ocurred while trying to update the client.");

        return updatedClient;
    }

    @Override
    public Client findById(Long id) {
        Client searchedClient = clientRepository.findOne(id);

        checkServiceStatus(searchedClient, "The client was searched succesfully.","An error ocurred while trying to search the client.");

        return searchedClient;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.delete(id);

        Client searchedClient = clientRepository.findOne(id);

        checkSuccessfullyDelete(searchedClient);
    }

    private void checkSuccessfullyDelete(Client client) {
        if (client == null) {
            logger.info("The client was deleted successfully");
        } else {
            logger.warn("An error ocurred while deleting the client");
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> allClients = (List<Client>) clientRepository.findAll();

        checkSuccessfullyFindAll(allClients);

        return allClients;
    }

    private void checkSuccessfullyFindAll(List<Client> allClients) {
        if (allClients != null) {
            logger.info("The clients were found successfully");
        } else {
            logger.warn("An error ocurred while searching clients");
        }
    }

    @Override
    public Client addPurcharse(Client client, Purcharse purcharse) {
        client.addPurcharse(purcharse);

        Client updatedClient = updateClient(client);

        checkServiceStatus(updatedClient, "The purcharse was added succesfully.","An error ocurred while trying to add the purcharse.");

        return updatedClient;
    }


}
