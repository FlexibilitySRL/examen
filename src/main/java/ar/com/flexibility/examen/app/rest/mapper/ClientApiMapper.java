package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Client;

/**
 * Mapper to transform {@link ClientApi} to {@link Client} and vice versa.
 */
public class ClientApiMapper implements EntityMapper<ClientApi, Client> {

    /**
     * {@inheritDoc}
     */
    public ClientApi buildApi(Client client) {
        return ClientApi.newBuilder()
                .setId(client.getId())
                .setDateCreated(client.getDateCreated())
                .setName(client.getName())
                .setDocumentId(client.getDocumentId())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    public Client buildEntity(ClientApi clientApi) {
        return new Client(
                clientApi.getId(),
                clientApi.getDateCreated(),
                clientApi.getName(),
                clientApi.getDocumentId());
    }
}
