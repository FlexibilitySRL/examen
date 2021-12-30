package ar.com.plug.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@Configuration
@ComponentScan("ar.com.plug")
public class Application {

    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
