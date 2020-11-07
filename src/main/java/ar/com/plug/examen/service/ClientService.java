package ar.com.plug.examen.service;

import ar.com.plug.examen.dao.ClientsDAO;
import ar.com.plug.examen.model.Clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientsDAO clientDao;

    Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public ClientService(ClientsDAO clientDao) {
        this.clientDao = clientDao;
    }

    public int insertClient(Clients client) {
        try {
            clientDao.save(client);
            logger.info("insertClient processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public int updateClient(Clients client) {
        try {
            clientDao.save(client);
            logger.info("updateClient processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public int deleteClient (Long id){
        try {
            clientDao.deleteById(id);
            logger.info("deleteClient processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public List<Clients> getAllClients() {
        List<Clients> users = clientDao.findAll();
        logger.info("getAllClients processed correctly");
        return users;
    }
}
