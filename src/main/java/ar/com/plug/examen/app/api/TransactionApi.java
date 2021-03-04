package ar.com.plug.examen.app.api;

import java.util.Date;
import java.util.List;

import ar.com.plug.examen.domain.Enums.TransactionStatusEnum;

public class TransactionApi {

	private Long id;

	private TransactionStatusEnum status;
	
	private List<TransactionDetailApi> detail;
	
	private Date date;
	
	private ClientApi client;
	
	private SellerApi seller;

	public TransactionApi() { }

	public TransactionApi(Long id, ClientApi client, SellerApi seller, TransactionStatusEnum status, Date date,
			List<TransactionDetailApi> detail) {
		this.id = id;
		this.client = client;
		this.seller = seller;
		this.status = status;
		this.date = date;
		this.detail = detail;
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

	public List<TransactionDetailApi> getDetail() {
		return detail;
	}

	public void setDetail(List<TransactionDetailApi> detail) {
		this.detail = detail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
