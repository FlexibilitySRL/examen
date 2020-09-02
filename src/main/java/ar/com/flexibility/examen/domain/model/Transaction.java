package ar.com.flexibility.examen.domain.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class Transaction {
	@Id
	@Column
	private Long id;
	@Column
	private Long number;
	@Column
	private Long clientId;
	@Column
	private Long productId;
	@Column
	private TransactionStatus transactionStatus;
	@Column
	private Date date;

	public Transaction(Long id, Long number, Long clientId, Long productId, TransactionStatus transactionStatus,
			Date date) {
		this.id = id;
		this.number = number;
		this.clientId = clientId;
		this.productId = productId;
		this.transactionStatus = transactionStatus;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
