package ar.com.plug.examen.domain.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.exceptions.BadRequestError;
import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.model.TransactionDTO;
import ar.com.plug.examen.domain.model.TransactionDTORequest;

@Component
public class Validator {

	
	public void validateClient(ClientDTO client) throws BadRequestError {
		if (StringUtils.isBlank(client.getFirstname())) {
			throw new BadRequestError("Firstname is required");
		}
		if(StringUtils.isBlank(client.getLastname())) {
			throw new BadRequestError("Lastname is required");
		}
		if(StringUtils.isBlank(client.getEmail())) {
			throw new BadRequestError("Email is required");
		}
	}

	public void validateTransaction(TransactionDTORequest transaction) throws BadRequestError {
		if (StringUtils.isBlank(transaction.getClientId().toString())) {
			throw new BadRequestError("Client ID is required");
		}
		if (transaction.getLsProductsQuantities().size() == 0) {
			throw new BadRequestError("Products are required");
		}
	}
	
	public void validateProduct(ProductDTO product) throws BadRequestError {
		if (StringUtils.isBlank(product.getName())) {
			throw new BadRequestError("Product name is required");
		}
		if (StringUtils.isBlank(product.getPrice().toString()) || product.getPrice() == 0) {
			throw new BadRequestError("Product price is required");
		}
		if(StringUtils.isBlank(String.valueOf(product.getStock())) || product.getStock() == 0) {
			throw new BadRequestError("Product stock is required");
		}
	}
	
}
