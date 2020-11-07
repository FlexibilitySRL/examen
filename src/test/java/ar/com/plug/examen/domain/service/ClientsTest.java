package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.dao.ClientsDAO;
import ar.com.plug.examen.model.Clients;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientsTest {

    @Autowired
    private ClientsDAO clientsDAO;

    @Test
    public void testCreateClient(){
        clientsDAO.save(new Clients("Pedro","Cardenas", 123L,123L,"Cra 1", true));
        Clients savedClient = clientsDAO.findByName("Pedro");
        assert(savedClient.getName()).equals("Pedro");
    }
    @Test
    public void testNoExistClient(){
        clientsDAO.save(new Clients("Pedro","Cardenas", 123L,123L,"Cra 1", true));
        Clients savedClient = clientsDAO.findByName("Pablo");
        Assert.assertNull(savedClient);
    }

    @Test
    public void testListClient(){
        List<Clients> products = clientsDAO.findAll();
        Assert.assertTrue(products.size()>0);
    }

    @Test
    public void testDeleteClient(){
        Long id = 5L;
        Optional<Clients> isExistBeforeDelete = clientsDAO.findById(id);
        clientsDAO.deleteById(id);
        Optional<Clients> isExistAfterDelete = clientsDAO.findById(id);
        Assert.assertTrue(isExistBeforeDelete.isPresent());
        Assert.assertFalse(isExistAfterDelete.isPresent());

    }
}
