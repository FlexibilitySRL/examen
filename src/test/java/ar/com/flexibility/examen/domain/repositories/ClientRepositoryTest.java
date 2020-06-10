package ar.com.flexibility.examen.domain.repositories;

import ar.com.flexibility.examen.domain.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClientRepository clientRepository;
    private Client client;

    @Before
    public void setup() {
        client = new Client();
        client.setName("Jose");

        client = entityManager.merge(client);
    }

    @Test
    public void create() {
        client = clientRepository.save(client);
        assertNotNull(client.getId());
    }

    @Test
    public void update() {
        Client clientToUpdate = new Client();
        clientToUpdate.setName("Maria");

        Client updated = clientRepository.save(clientToUpdate);
        assertNotNull(updated);
        assertEquals(clientToUpdate.getName(), updated.getName());
    }

    @Test
    public void retrieve() {
        Client retrieved = clientRepository.findById(client.getId()).orElse(null);
        assertNotNull(retrieved);
        assertNotNull(retrieved.getId());
        assertEquals(client.getId(), retrieved.getId());
    }

    @Test
    public void list() {
        List<Client> lists = clientRepository.findAll();
        assertNotNull(lists);
        assertFalse(lists.isEmpty());
    }

    @Test
    public void delete() {
        Client client = new Client();
        client.setName("Jose");
        client = entityManager.merge(client);

        clientRepository.delete(client.getId());

        assertFalse(clientRepository.findById(client.getId()).isPresent());
    }

}