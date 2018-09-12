package ar.com.flexibility.examen.domain.service.mapper;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Client and its Api ClientApi.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ClientMapper extends EntityMapper<ClientApi, Client> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ClientApi toApi(Client client);

    @Mapping(source = "userId", target = "user")
    Client toEntity(ClientApi clientApi);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
