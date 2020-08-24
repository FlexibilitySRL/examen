package ar.com.flexibility.examen;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("ar.com.flexibility.examen")
@EntityScan({"ar.com.flexibility.examen.domain.model"})
@EnableJpaRepositories(basePackages={"ar.com.flexibility.examen.repository"})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
