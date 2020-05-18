package ar.com.flexibility.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@ComponentScan("ar.com.flexibility.examen") //@SpringBootAplication already adds component scan at default package
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
