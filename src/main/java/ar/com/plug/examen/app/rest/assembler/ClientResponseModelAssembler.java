package ar.com.plug.examen.app.rest.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.client.ClientResponseApi;
import ar.com.plug.examen.app.rest.ClientController;
import ar.com.plug.examen.domain.model.Client;

@Component
public class ClientResponseModelAssembler implements RepresentationModelAssembler<Client, EntityModel<ClientResponseApi>> {

	private ModelMapper mapper = new ModelMapper();

	@Override
	public EntityModel<ClientResponseApi> toModel(Client entity) {
		return EntityModel.of(mapper.map(entity, ClientResponseApi.class),
				linkTo(methodOn(ClientController.class).findById(entity.getId())).withSelfRel());
	}

}
