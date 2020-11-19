package ar.com.plug.examen.app.api;

import java.util.Date;
import java.util.List;

public class TransactionApi {

	private Long id;

	private ClientApi client;

	private SellerApi seller;

	private String status;

	private Date date;

	private List<TransactionDetailApi> transactionDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<TransactionDetailApi> getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(List<TransactionDetailApi> transactionDetail) {
		this.transactionDetail = transactionDetail;
	}
}