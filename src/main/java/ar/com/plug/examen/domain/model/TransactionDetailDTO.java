package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.plug.examen.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDetailDTO {

	private ProductDTO product;
	private int quantity;
	
	@JsonIgnore
	private Transaction transaction;
	
}
