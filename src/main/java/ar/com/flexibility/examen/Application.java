package ar.com.flexibility.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableJpaAuditing
@EnableTransactionManagement
@SpringBootApplication
public class Application {

	public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
