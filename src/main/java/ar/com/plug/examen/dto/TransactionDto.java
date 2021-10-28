package ar.com.plug.examen.dto;

import java.util.List;

import ar.com.plug.examen.dao.entities.Client;
import ar.com.plug.examen.dao.entities.Product;
import ar.com.plug.examen.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
