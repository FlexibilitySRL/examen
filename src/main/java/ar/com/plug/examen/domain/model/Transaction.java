package ar.com.plug.examen.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Long productId;
	private Long clientId;
	private Long sellerId;
	private Double price;
	private String status;
	private LocalDateTime date;
	
}
