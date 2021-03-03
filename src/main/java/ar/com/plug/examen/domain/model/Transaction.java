package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
	private List<TransactionDetail> detail;
	
	@Column
	private Date date;

	
	@Column
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", referencedColumnName = "id")
	private Client client;
	
	@Column
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_seller", referencedColumnName = "id")
	private Seller seller;
	
	public Transaction () {}
	
	public Transaction (Long id, Client client, Seller seller, List<TransactionDetail> detail, TransactionStatusEnum status, Date date) {
		this.id = id;
		this.status = status;
		this.detail = detail;
		this.date = date;
		this.client = client;
		this.seller = seller;
	}
	
	public Transaction (Client client, Seller seller, List<TransactionDetail> detail, TransactionStatusEnum status, Date date ) {
		this.status = status;
		this.detail = detail;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public List<TransactionDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<TransactionDetail> detail) {
		this.detail = detail;
	}
	
}
