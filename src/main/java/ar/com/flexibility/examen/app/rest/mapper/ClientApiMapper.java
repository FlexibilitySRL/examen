package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Client;

public class ClientApiMapper implements EntityMapper<ClientApi, Client> {

    public ClientApi buildApi(Client client) {
        return ClientApi.newBuilder()
                .setId(client.getId())
                .setDateCreated(client.getDateCreated())
                .setName(client.getName())
                .setDocumentId(client.getDocumentId())
                .build();
    }

    public Client buildEntity(ClientApi clientApi) {
        return new Client(
                clientApi.getId(),
                clientApi.getDateCreated(),
                clientApi.getName(),
                clientApi.getDocumentId());
    }
}
