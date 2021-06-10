package ar.com.plug.examen.domain.service;
import ar.com.plug.examen.domain.model.ClientModel;
import java.util.List;
/**
 *
 * @author AGB
 */
public interface ClientService {
    
   ClientModel saveClient(ClientModel product);
   
   List<ClientModel> saveClients(List<ClientModel> products);
   
   List<ClientModel> getClients();
   
   ClientModel getClientById(int id);
   
   ClientModel getClientByEmail(String name);
   
   String deleteClient(int id);
   
   ClientModel updateClient(ClientModel product);

}
