package ar.com.plug.examen.app.fixtures;

import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.domain.model.Client;
import java.util.ArrayList;
import java.util.List;


public class ClientFixture {

    public static ClientDto getClientApiWithId(Long id) {
        ClientDto clientApi = ClientDto.builder()
                .id(id)
                .build();
        return clientApi;
    }

    public static ClientDto getClientApi() {
        ClientDto clientApi = ClientDto.builder()
                .userName("matiasrh84")
                .firstName("Matías")
                .lastName("Ruiz Holgado")
                .age(37L)
                .email("matiasrh84@gmail.com")
                .build();
        return clientApi;
    }

    public static List<ClientDto> getClientApitList(ClientDto clientApi1, ClientDto clientApi2) {
        List<ClientDto> lsClients = new ArrayList<>();
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
