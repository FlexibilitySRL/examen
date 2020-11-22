package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ar.com.plug.examen.domain.enums.StatusEnum;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", referencedColumnName = "id")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_seller", referencedColumnName = "id")
	private Seller seller;

	@Column
	private StatusEnum status;

	@Column
	private Date date;

	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
	private List<TransactionDetail> transactionDetail;

	public Transaction() {
	}

	public Transaction(Long id, Client client, Seller seller, StatusEnum status, Date date,
			List<TransactionDetail> transactionDetail) {
		this.id = id;
		this.client = client;
		this.seller = seller;
		this.status = status;
		this.date = date;
		this.transactionDetail = transactionDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<TransactionDetail> getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(List<TransactionDetail> transactionDetail) {
		this.transactionDetail = transactionDetail;
	}

	public static class Builder {
		private Long id;
		private Client client;
		private Seller seller;
		private StatusEnum status;
		private Date date;
		private List<TransactionDetail> transactionDetail;

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setClient(Client client) {
			this.client = client;
			return this;
		}

		public Builder setSeller(Seller seller) {
			this.seller = seller;
			return this;
		}

		public Builder setStatus(StatusEnum status) {
			this.status = status;
			return this;
		}

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setTransactionDetail(List<TransactionDetail> transactionDetailList) {
			this.transactionDetail = transactionDetailList;
			return this;
		}

		public Builder setTransactionDetail(TransactionDetail transactionDetail) {
			this.transactionDetail = Arrays.asList(transactionDetail);
			return this;
		}
		
		public Transaction build() {
			return new Transaction(id, client, seller, status, date, transactionDetail);
		}
	}
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", client=" + client.toString() 
				+ ", seller=" + seller.toString() + ", transactionDetail=" + transactionDetail.toString() 
				+ ", status=" + status.toString() + ", date=" + date.toString() + "]";
	}

}