package ar.com.flexibility.examen;

import ar.com.flexibility.examen.utils.LoggingInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan("ar.com.flexibility.examen")
public class Application extends WebMvcConfigurerAdapter {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }
}