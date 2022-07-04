package ar.com.plug.examen.domain.service.repository;

import ar.com.plug.examen.app.rest.model.Client;
import ar.com.plug.examen.app.rest.model.Seller;
import ar.com.plug.examen.app.rest.repositories.ClientRepository;
import ar.com.plug.examen.app.rest.repositories.SellerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(scripts = {"/test-mysql.sql"})
@TestPropertySource(locations = "classpath:db-test.properties")
public class SellerRepositoryTest
{
    @Autowired
    private SellerRepository repository;

    private Seller seller;

    @Before
    public void setup()
    {
        seller = new Seller("Gustavo Vendedor",true);
    }

    @Test
    public void autowiredNotNull()
    {
        assertThat(repository).isNotNull();
    }

    @Test
    public void testSave()
    {
        Seller savedEntity = repository.save(seller);
        assertThat(savedEntity.getId()).isGreaterThan(0);
        assertThat(savedEntity.getActive()).isEqualTo(Boolean.TRUE);
        assertThat(savedEntity.getName()).isEqualTo("Gustavo Vendedor");
    }

    @Test
    public void testGet()
    {
        Seller savedEntity = repository.findById(1L).get();
        assertThat(savedEntity.getId()).isGreaterThan(0);
        assertThat(savedEntity.getActive()).isEqualTo(Boolean.TRUE);
        assertThat(savedEntity.getName()).isEqualTo("Gustavo Vendedor");
    }
}
