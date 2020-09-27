package ar.com.plug.examen.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "ar.com.plug.examen",
        entityManagerFactoryRef = "entityManager"
)
public class TestDatasourceConfiguration {

    @Bean
    public DataSource testDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:test;DB_CLOSE_DELAY=-1");
        //dataSource.setUrl("jdbc:hsqldb:~/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManager() throws NamingException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(testDataSource());
        em.setPackagesToScan(new String[] { "ar.com.plug.examen" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.show_sql", "true");
        em.setJpaPropertyMap(properties);

        return em;
    }


}

