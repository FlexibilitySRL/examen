package ar.com.plug.examen.service;

import ar.com.plug.examen.dao.ClientsDAO;
import ar.com.plug.examen.model.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientsDAO clientDao;

    @Autowired
    public ClientService(ClientsDAO clientDao) {
        this.clientDao = clientDao;
    }

    public int insertClient(Clients client) {
        try {
            clientDao.save(client);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateClient(Clients client) {
        try {
            clientDao.save(client);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int deleteClient (Long id){
        try {
            clientDao.deleteById(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Clients> getAllClients() {
        List<Clients> users = clientDao.findAll();
        return users;
    }
}
