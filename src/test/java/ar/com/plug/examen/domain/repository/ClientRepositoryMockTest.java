package ar.com.plug.examen.domain.repository;


import ar.com.plug.examen.domain.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ClientRepositoryMockTest {

     @Autowired
    private ClientRepository clientRepository;

     @Test
    public void whenFindByNumberId_thenReturnClient(){
         Client client = Client.builder()
                 .firstName("Juan")
                 .lastName("Perez")
                 .email("juan@perez.com")
                 .numberID("12345678")
                 .build();

         clientRepository.save(client);
         Client clientFound = clientRepository.findByNumberID(client.getNumberID());
         Assertions.assertThat(clientFound).isEqualTo(client);
     }

    @Test
    public void whenFindByLastName_thenReturnClient(){
        Client client = Client.builder()
                .firstName("Carlos")
                .lastName("Perez")
                .email("carlos@perez.com")
                .numberID("32145678")
                .build();

        clientRepository.save(client);
        List<Client> clientFound = clientRepository.findByLastName(client.getLastName());
        Assertions.assertThat(clientFound.size()).isEqualTo(1);
    }


    @Test
    public void whenFindByEmail_thenReturnClient(){
        Client client = Client.builder()
                .firstName("Jose")
                .lastName("Perez")
                .email("jose@perez.com")
                .numberID("12365478")
                .build();

        clientRepository.save(client);
        Client clientFound = clientRepository.findByEmail(client.getEmail());
        Assertions.assertThat(clientFound).isEqualTo(client);
    }
}
