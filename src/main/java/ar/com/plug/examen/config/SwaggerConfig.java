package ar.com.plug.examen.config;

import static com.google.common.base.Predicates.or;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${spring.profiles.active}")
	private String perfilActivo;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("ar.com.plug.examen.app.rest"))
				.paths(getPaths()).build().apiInfo(metaData());
	}

	@SuppressWarnings("unchecked")
	private Predicate<String> getPaths() {
		return or(PathSelectors.regex("/"), PathSelectors.regex("/client.*"), PathSelectors.regex("/transaction.*"), PathSelectors.regex("/product.*"));
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Flexibility WS API").description("Ambiente: " + perfilActivo).version(StringUtils.EMPTY).license(StringUtils.EMPTY)
				.licenseUrl(StringUtils.EMPTY).contact(new Contact(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY)).build();
	}
	
	 @Bean
	  public LinkDiscoverers discoverers() {
	    List<LinkDiscoverer> plugins = new ArrayList<>();
	    plugins.add(new CollectionJsonLinkDiscoverer());
	    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	  }

}
