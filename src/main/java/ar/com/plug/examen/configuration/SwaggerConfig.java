package ar.com.plug.examen.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableHypermediaSupport(type=EnableHypermediaSupport.HypermediaType.HAL)
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket usersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(usersApiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex("/.*"))
                //.apis(RequestHandlerSelectors.basePackage("ar.com.plug.examen.app.rest"))
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo usersApiInfo() {
        return new ApiInfoBuilder()
                .title("Service documentation - Flexibility Payments")
                .version("1.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
