package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.domain.model.Client;

import java.util.List;

public interface ClientService {
    public List<Client> listAllClient();
    public Client getClient(Long id);
    public Client createClient(Client client);
    public Client updateClient(Client client);
    public  Client deleteClient(Long id);
    public Client findByEmail(String email);
}
