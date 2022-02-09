package ar.com.plug.examen.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{
	@Bean
	public Docket apiDocket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("ar.com.plug.examen.app.rest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}

	private ApiInfo getApiInfo()
	{
		return new ApiInfo(
				"FlexibilitySRL API",
				"FlexibilitySRL Description",
				"1.0",
				"Terms of service",
				new Contact("Yeisson Boada", "https://google.com", "yeissonboada@gmail.com"),
				"License of API",
				"API license URL",
				Collections.emptyList());

	}

	@Bean
	public LinkDiscoverers discoverers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}

}
