package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.dao.SellersDAO;
import ar.com.plug.examen.model.Sellers;
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
public class SellersTest {

    @Autowired
    private SellersDAO sellersDAO;

    @Test
    public void testCreateSeller(){
        sellersDAO.save(new Sellers("Luis","Gonzalez", 123L,123L, true));
        Sellers savedSeller = sellersDAO.findByName("Luis");
        assert(savedSeller.getName()).equals("Luis");
    }
    @Test
    public void testNoExistSeller(){
        sellersDAO.save(new Sellers("Luis","Gonzalez", 123L,123L, true));
        Sellers savedSeller = sellersDAO.findByName("Pedro");
        Assert.assertNull(savedSeller);
    }

    @Test
    public void testListSeller(){
        List<Sellers> sellers = sellersDAO.findAll();
        Assert.assertTrue(sellers.size()>0);
    }

    @Test
    public void testDeleteClient(){
        Long id = 8L;
        Optional<Sellers> isExistBeforeDelete = sellersDAO.findById(id);
        sellersDAO.deleteById(id);
        Optional<Sellers> isExistAfterDelete = sellersDAO.findById(id);
        Assert.assertTrue(isExistBeforeDelete.isPresent());
        Assert.assertFalse(isExistAfterDelete.isPresent());

    }

}
