package ar.com.plug.examen.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ar.com.plug.examen.component.RestRequestResponseInterceptor;

@Configuration
public class RestClientConfig implements WebMvcConfigurer  {
	@Autowired
	RestRequestResponseInterceptor restRequestResponseInterceptor; 
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(restRequestResponseInterceptor);
	}
}
