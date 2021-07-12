package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.model.Client;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

  public ClientApi clientToClientApi(Client client) {
    ClientApi clientApi = new ClientApi();
    clientApi.setId(client.getId());
    clientApi.setUserName(client.getUserName());
    clientApi.setFirstName(client.getFirstName());
    clientApi.setLastName(client.getLastName());
    clientApi.setAge(client.getAge());
    clientApi.setEmail(client.getEmail());
    return clientApi;
  }

  public Client clientApiToClient(ClientApi clientApi) {
    Client client = new Client();
    client.setId(clientApi.getId());
    client.setUserName(clientApi.getUserName());
    client.setFirstName(clientApi.getFirstName());
    client.setLastName(clientApi.getLastName());
    client.setAge(clientApi.getAge());
    client.setEmail(clientApi.getEmail());
    return client;
  }

  public List<ClientApi> clientsToListClientApi(List<Client> clients) {
    return clients.stream().map(this::clientToClientApi).collect(Collectors.toList());
  }

}
