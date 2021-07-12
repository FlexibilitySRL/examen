package fixtures;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.model.Client;
import java.util.ArrayList;
import java.util.List;

public class ClientFixture {

  public static ClientApi getClientApiWithId(Long id) {
    ClientApi clientApi = new ClientApi();
    clientApi.setId(id);
    return clientApi;
  }

  public static ClientApi getClientApi() {
    ClientApi clientApi = new ClientApi();
    clientApi.setUserName("jtarragona");
    clientApi.setFirstName("Juan");
    clientApi.setLastName("Tarragona");
    clientApi.setAge(34L);
    clientApi.setEmail("jtarragona@hexacta.com");
    return clientApi;
  }

  public static List<ClientApi> getClientApitList(ClientApi clientApi1, ClientApi clientApi2) {
    List<ClientApi> lsClients = new ArrayList<ClientApi>();
    lsClients.add(clientApi1);
    lsClients.add(clientApi2);
    return lsClients;
  }

  public static Client getClientWithId(Long id) {
    Client client = new Client();
    client.setId(id);
    return client;
  }

  public static Client getClient() {
    Client client = new Client();
    client.setUserName("jtarragona");
    client.setUserName("Juan");
    client.setLastName("Tarragona");
    client.setAge(34L);
    client.setEmail("jtarragona@hexacta.com");
    return client;
  }

}
