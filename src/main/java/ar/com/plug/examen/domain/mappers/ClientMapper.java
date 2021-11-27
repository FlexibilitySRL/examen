package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.model.Client;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class ClientMapper {

    public ClientApi clientToClientApi(Client client) {

        ClientApi clientApi = ClientApi.builder()
                .id(client.getId())
                .userName(client.getUserName())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .email(client.getEmail())
                .build();

        return clientApi;
    }
    
    public Client clientApiToClient(ClientApi clientApi) {

        Client client = Client.builder()
                .id(clientApi.getId())
                .userName(clientApi.getUserName())
                .firstName(clientApi.getFirstName())
                .lastName(clientApi.getLastName())
                .age(clientApi.getAge())
                .email(clientApi.getEmail())
                .build();

        return client;
    }
    
    public List<ClientApi> clientsToListClientApi(List<Client> clients) {
    return clients.stream().map(this::clientToClientApi).collect(Collectors.toList());
  }

}

