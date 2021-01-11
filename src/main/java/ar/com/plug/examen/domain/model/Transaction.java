package ar.com.plug.examen.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class Transaction {

	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "transaction_productId")
	private Long productId;
	@Column(name = "transaction_clientId")
	private Long clientId;
	@Column(name = "transaction_sellerId")
	private Long sellerId;
	@Column(name = "transaction_price")
	private Double price;
	@Column(name = "transaction_status")
	private String status;
	@Column(name = "transaction_date")
	private LocalDateTime date;
	
}
