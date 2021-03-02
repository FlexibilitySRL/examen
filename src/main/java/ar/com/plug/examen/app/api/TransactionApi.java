package ar.com.plug.examen.app.api;

import java.util.Date;

import ar.com.plug.examen.domain.Enums.TransactionStatusEnum;

public class TransactionApi {

	private Long id;

	private TransactionStatusEnum status;
	
	private String transactionDetail;
	
	private Date date;

	private Long amount;
	
	private ClientApi client;
	
	private SellerApi seller;

	public TransactionApi() { }

	public TransactionApi(Long id, ClientApi client, SellerApi seller, TransactionStatusEnum status, Date date,
			String transactionDetail) {
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

	public TransactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}

	public String getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(String transactionDetail) {
		this.transactionDetail = transactionDetail;
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

	public ClientApi getClient() {
		return client;
	}

	public void setClient(ClientApi client) {
		this.client = client;
	}

	public SellerApi getSeller() {
		return seller;
	}

	public void setSeller(SellerApi seller) {
		this.seller = seller;
	}
	
}
