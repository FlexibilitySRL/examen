package ar.com.plug.examen.domain.service.repository;

import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.repositories.ProductRepository;
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
public class ProductRepositoryTest
{
    @Autowired
    private ProductRepository repository;
    private Product product1;
    @Before
    public void setup()
    {
        product1 = new Product("sopa",true,10);

    }

    @Test
    public void autowiredNotNull()
    {
        assertThat(repository).isNotNull();
    }

    @Test
    public void testSave()
    {
        Product savedEntity = repository.save(product1);
        assertThat(savedEntity.getId()).isGreaterThan(0);
        assertThat(savedEntity.getActive()).isEqualTo(Boolean.TRUE);
        assertThat(savedEntity.getDescription()).isEqualTo("sopa");
    }

    @Test
    public void testGet()
    {
        Product savedEntity = repository.findById(1L).get();
        assertThat(savedEntity.getId()).isGreaterThan(0);
        assertThat(savedEntity.getActive()).isEqualTo(Boolean.TRUE);
        assertThat(savedEntity.getDescription()).isEqualTo("sopa");
    }
}
