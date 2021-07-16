package ar.com.plug.examen.domain.model;

import java.util.Date;
import java.util.List;

import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDTO {

	private Long id;
	private ClientDTO client;
	private List<TransactionDetailDTO> transactionDetails;
	private Integer status;
	private Date transactionDate;

	
}
