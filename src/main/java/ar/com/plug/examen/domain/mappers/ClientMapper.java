package ar.com.plug.examen.domain.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.domain.model.Client;


@Component
public class ClientMapper {

    public ClientDto clientToClientApi(Client client) {

        ClientDto clientApi = ClientDto.builder()
                .id(client.getId())
                .userName(client.getUserName())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .email(client.getEmail())
                .build();

        return clientApi;
    }
    
    public Client clientApiToClient(ClientDto clientApi) {

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
    
    public List<ClientDto> clientsToListClientApi(List<Client> clients) {
    return clients.stream().map(this::clientToClientApi).collect(Collectors.toList());
  }

}

