package ar.com.plug.examen.domain.mapper;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.domain.model.Client;

public class ClientMapper {

    public static Client toClient(ClientApi clientApi) {
        return Client.builder()
                .name(clientApi.getName())
                .email(clientApi.getEmail())
                .build();
    }

    public static ClientDto toClientDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }

    public static Client updateClient(Client client, ClientApi clientApi) {
        client.setName(clientApi.getName());
        client.setEmail(clientApi.getEmail());
        return client;
    }
}
