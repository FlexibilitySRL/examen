package ar.com.flexibility.examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.flexibility.examen.repo.IProductoRepo;

@SpringBootApplication
@ComponentScan("ar.com.flexibility.examen")
public class Application {
	
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

