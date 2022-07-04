package ar.com.plug.examen.domain.service.repository;

import ar.com.plug.examen.app.rest.model.Client;
import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.model.Purchase;
import ar.com.plug.examen.app.rest.model.Seller;
import ar.com.plug.examen.app.rest.repositories.ClientRepository;
import ar.com.plug.examen.app.rest.repositories.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(scripts = {"/test-mysql.sql"})
@TestPropertySource(locations = "classpath:db-test.properties")
public class PurchaseRepositoryTest
{
    @Autowired
    private PurchaseRepository repository;

    private Purchase purchase;
    private Client client;

    private Seller seller;

    private Product product;

    @Before
    public void setup()
    {
        product = new Product(1L,"sopa",true,10);
        client = new Client(1L,"Gc","gucompi@gmail.com",true);
        seller = new Seller(1L,"Gustavo Vendedor",true);
        purchase = new Purchase(new BigDecimal(10),5,false,client,product,seller);
    }

    @Test
    public void autowiredNotNull()
    {
        assertThat(repository).isNotNull();
    }

    @Test
    public void testSave()
    {
        Purchase savedEntity = repository.save(purchase);
        assertThat(savedEntity.getId()).isGreaterThan(0);
    }

    @Test
    public void testGet()
    {
        Purchase savedEntity = repository.findById(1L).get();
        assertThat(savedEntity.getId()).isGreaterThan(0);
    }
}
