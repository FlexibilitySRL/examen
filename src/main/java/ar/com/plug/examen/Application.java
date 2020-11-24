package ar.com.plug.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan("ar.com.plug.examen")
@EntityScan({"ar.com.plug.examen.domain.model"})
@EnableJpaRepositories(basePackages={"ar.com.plug.examen.domain.repository"})
public class Application {

    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
