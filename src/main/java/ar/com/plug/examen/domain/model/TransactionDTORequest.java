package ar.com.plug.examen.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDTORequest {
	
	private Long clientId;
	private Long sellerId;
	private List<ProductQuantityDTO> lsProductsQuantities;



}
