package ar.com.plug.examen.domain.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.plug.examen.domain.Enums.TransactionStatusEnum;

@Entity
@Table
public class Transaction {
	
	@Id
	private Long id;
	
	@Column
	private TransactionStatusEnum status;
	
	@Column
	private String details;
	
	@Column
	private Long number;
	
	@Column
	private Date date;
	
	@Column
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", referencedColumnName = "id")
	private Client client;
}
