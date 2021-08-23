package ar.com.plug.examen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket clienteApi() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.select()                                  
				.apis(RequestHandlerSelectors.basePackage("ar.com.plug.examen.app.rest"))              
				.paths(PathSelectors.any())                          
				.build()
				.apiInfo(apiInfo());                                           
	}
	
	private ApiInfo apiInfo() {
		return  new ApiInfoBuilder()
				.title("Spring Boot Swagger Configutarion")
				.description("\"Swagger configuration for application exam \"")
				.version("1.0.0")
				.license("Apache 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
