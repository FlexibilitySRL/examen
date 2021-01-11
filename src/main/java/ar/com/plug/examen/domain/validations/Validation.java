package ar.com.plug.examen.domain.validations;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.execptions.BadRequestException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Validation {
	
	public PairResult validateProduct(ProductApi product) {
		
		String leyend = "";
		boolean valid = true;
		
		if(product.getName().isBlank()) {
			leyend = "The name is required, ";
			valid = false;
		}else if(product.getPrice().isNaN()) {
			leyend = leyend + "The price is required or must be a number, ";
			valid = false;
		}else if(product.getDescription().isBlank()) {
			leyend = leyend + "The description is required.";
			valid = false;
		}
		
		return new PairResult(valid, leyend);
	}
	
	public PairResult validateClient(ClientApi client) {
		String leyend = "";
		boolean valid = true;
		
		if(client.getName().isBlank()) {
			leyend = "The name is required. ";
			valid = false;
		}
		
		return new PairResult(valid, leyend);
	}
	
	public PairResult validateSeller(SellerApi seller) {
		String leyend = "";
		boolean valid = true;
		
		if(seller.getName().isBlank()) {
			leyend = "The name is required. ";
			valid = false;
		}
		
		return new PairResult(valid, leyend);
	}
	
	public PairResult validateTransaction(TransactionApi transaction) {
		
		String leyend = "";
		boolean valid = true;
		
		if(transaction.getClientId() != null) {
			leyend = "The client id is required, ";
			valid = false;
		}else if(transaction.getPrice().isNaN() || transaction.getPrice() == null || transaction.getPrice() <= 0) {
			leyend = leyend + "The price is required or must be a number, ";
			valid = false;
		}else if(transaction.getProductId() != null) {
			leyend = leyend + "The product id is required, ";
			valid = false;
		}else if(transaction.getSellerId() != null) {
			leyend = leyend + "The seller id is required, ";
			valid = false;
		}else if(transaction.getStatus().isBlank()) {
			leyend = leyend + "The status is required";
			valid = false;
		}
		
		return new PairResult(valid, leyend);
	}
	
	public void validateId(Long firstId, Long secondId) {
		if(secondId != firstId) {
			log.error("The ids do not match. wanted id:" + secondId  + "!=  param id:" + firstId);
			throw new BadRequestException("The ids do not match. wanted id:" + secondId  + "!=  param id:" + firstId);
		}else if(secondId == null || secondId <= 0) {
			log.error("The id is required for the update. ");
			throw new BadRequestException("Mandatory data is missing: id");
		}
	}
	
}

















