package ar.com.plug.examen.app.rest.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.transaction.TransactionResponseApi;
import ar.com.plug.examen.app.rest.TransactionController;
import ar.com.plug.examen.domain.model.Transaction;

@Component
public class TransactionResponseModelAssembler implements RepresentationModelAssembler<Transaction, EntityModel<TransactionResponseApi>> {

	private ModelMapper mapper = new ModelMapper();

	@Override
	public EntityModel<TransactionResponseApi> toModel(Transaction entity) {
		TransactionResponseApi content = mapper.map(entity, TransactionResponseApi.class);
		return EntityModel.of(content,
				linkTo(methodOn(TransactionController.class).findById(entity.getId())).withSelfRel());
	}

}
