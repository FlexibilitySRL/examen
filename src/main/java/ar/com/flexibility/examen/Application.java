package ar.com.flexibility.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("ar.com.flexibility.examen")
public class Application {	

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
