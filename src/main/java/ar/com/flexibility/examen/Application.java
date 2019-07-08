package ar.com.flexibility.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@ComponentScan("ar.com.flexibility.examen")
public class Application {
	
	private static Logger LOG = (Logger) LoggerFactory.getLogger(Application.class);
	

 
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }  
    
}
