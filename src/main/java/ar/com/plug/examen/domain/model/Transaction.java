package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.util.Date;

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
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column
	private TransactionStatusEnum status;
	
	@Column
	private String details; //TransactionDetails should be another entity, but I leave it this way to keep it simple on the exam
	
	@Column
	private Long number;
	
	@Column
	private Date date;

	@Column
	private Long amount;
	
	@Column
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", referencedColumnName = "id")
	private Client client;
	
	@Column
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_seller", referencedColumnName = "id")
	private Seller seller;
	
	public Transaction () {}
	
	public Transaction (Long id, TransactionStatusEnum status, String details, Long number, Date date, Client client, Seller seller) {
		this.id = id;
		this.status = status;
		this.details = details;
		this.date = date;
		this.client = client;
		this.seller = seller;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
}
