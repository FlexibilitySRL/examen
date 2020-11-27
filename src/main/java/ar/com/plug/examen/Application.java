package ar.com.plug.examen;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("ar.com.plug")
public class Application {

    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }
}
