package ar.com.flexibility.examen.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ar.com.flexibility"))
                .paths(paths())
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/");
             
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		.title("Exam - Spring boot - flexibility")
                .description("Jonathan Madrigale Exam")
                .version("0.1")
                .license("Unlicense")
                .build();
    }

        
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    

	private Predicate<String> paths() {
	// Match all paths except /error
	    return Predicates.and(
	    PathSelectors.any(),
	    Predicates.not(PathSelectors.regex("/error.*")));
	}
}