package ar.com.plug.examen.app.fixtures;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.model.Client;
import java.util.ArrayList;
import java.util.List;


public class ClientFixture {

    public static ClientApi getClientApiWithId(Long id) {
        ClientApi clientApi = ClientApi.builder()
                .id(id)
                .build();
        return clientApi;
    }

    public static ClientApi getClientApi() {
        ClientApi clientApi = ClientApi.builder()
                .userName("matiasrh84")
                .firstName("Matías")
                .lastName("Ruiz Holgado")
                .age(37L)
                .email("matiasrh84@gmail.com")
                .build();
        return clientApi;
    }

    public static List<ClientApi> getClientApitList(ClientApi clientApi1, ClientApi clientApi2) {
        List<ClientApi> lsClients = new ArrayList<>();
        lsClients.add(clientApi1);
        lsClients.add(clientApi2);
        return lsClients;
    }

    public static Client getClientWithId(Long id) {
        Client client = Client.builder()
                .id(id)
                .build();
        return client;
    }

    public static Client getClient() {
        Client client = Client.builder()
                .userName("matiasrh84")
                .firstName("Matías")
                .lastName("Ruiz Holgado")
                .age(37L)
                .email("matiasrh84@gmail.com")
                .build();
        return client;
    }
}
