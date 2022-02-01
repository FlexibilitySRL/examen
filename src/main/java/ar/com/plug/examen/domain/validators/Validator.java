package ar.com.plug.examen.domain.validators;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.api.OrderRequest;
import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.domain.exception.ClientBadRequestException;
import ar.com.plug.examen.domain.exception.OrderBadRequestException;
import ar.com.plug.examen.domain.exception.ProductBadRequestException;
import ar.com.plug.examen.domain.exception.SellerBadRequestException;
import org.springframework.stereotype.Component;


@Component
public class Validator
{
	private static final String MESSAGE_ID = "The id is required";
	public void validateClientDTO(ClientDTO clientDTO, boolean savedId) {
		if (savedId && clientDTO.getId() == null) {
			throw new ClientBadRequestException(MESSAGE_ID);
		}

		if (clientDTO.getDocumentId() == null) {
			throw new ClientBadRequestException("The document id is required");
		}

		if (clientDTO.getFirstName() == null) {
			throw new ClientBadRequestException("The first name is required");
		}

		if (clientDTO.getLastName() == null) {
			throw new ClientBadRequestException("The last name is required");
		}

		if (clientDTO.getDocumentType() == null) {
			throw new ClientBadRequestException("The document type is required");
		}

		if (clientDTO.getEmail() == null) {
			throw new ClientBadRequestException("The email is required");
		}
	}

	public void validateProductDTO(ProductDTO productDTO, boolean savedId) {
		if (productDTO.getId() == null && savedId) {
			throw new ProductBadRequestException(MESSAGE_ID);
		}
		if (productDTO.getName() == null) {
			throw new ProductBadRequestException("The name is required");
		}
		if (productDTO.getDescription() == null) {
			throw new ProductBadRequestException("The description is required");
		}
		if (productDTO.getPrice() == null || productDTO.getPrice().isNaN()) {
			throw new ProductBadRequestException("The price is required or must be a number");
		}
		if (productDTO.getStock() == null) {
			throw new ProductBadRequestException("The stock is required");
		}
	}

	public void validateSellerDTO(SellerDTO sellerDTO, boolean savedId) {
		if (savedId && sellerDTO.getId() == null) {
			throw new SellerBadRequestException(MESSAGE_ID);
		}

		if (sellerDTO.getDocumentId() == null) {
			throw new SellerBadRequestException("The document id is required");
		}

		if (sellerDTO.getFirstName() == null) {
			throw new SellerBadRequestException("The first name is required");
		}

		if (sellerDTO.getLastName() == null) {
			throw new SellerBadRequestException("The last name is required");
		}

		if (sellerDTO.getDocumentType() == null) {
			throw new SellerBadRequestException("The document type is required");
		}

		if (sellerDTO.getEmail() == null) {
			throw new SellerBadRequestException("The email is required");
		}
	}


	public void validateOrder(OrderRequest orderRequest)
	{
		if (orderRequest.getClientId() == null) {
			throw new OrderBadRequestException("The client id is required");
		}
		if (orderRequest.getSellerId() == null) {
			throw new OrderBadRequestException("The seller id is required");
		}
		if (orderRequest.getProductRequests().isEmpty()) {
			throw new OrderBadRequestException("The products cannot be empty");
		}

	}

}
