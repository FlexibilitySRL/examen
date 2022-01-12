package ar.com.plug.examen.app.rest.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.product.ProductResponseApi;
import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.domain.model.Product;

@Component
public class ProductResponseModelAssembler implements RepresentationModelAssembler<Product, EntityModel<ProductResponseApi>> {

	private ModelMapper mapper = new ModelMapper();

	@Override
	public EntityModel<ProductResponseApi> toModel(Product entity) {
		return EntityModel.of(mapper.map(entity, ProductResponseApi.class),
				linkTo(methodOn(ProductController.class).findById(entity.getId())).withSelfRel());
	}

}
