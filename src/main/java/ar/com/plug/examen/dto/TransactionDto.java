package ar.com.plug.examen.dto;

import java.util.List;

import ar.com.plug.examen.domain.model.entities.Client;
import ar.com.plug.examen.domain.model.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {

	private Long id;
	
	private String status;
	
	private List<Product> products;
	
	private Client client;
	
	private int totalValue;
	
}
