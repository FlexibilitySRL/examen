package ar.com.plug.examen.app.api;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ar.com.plug.examen.domain.enums.StatusEnum;

public class TransactionApi {

	private Long id;

	private ClientApi client;

	private SellerApi seller;

	private StatusEnum status;

	private Date date;

	private List<TransactionDetailApi> transactionDetail;

	public TransactionApi() { }
	
	public TransactionApi(Long id, ClientApi client, SellerApi seller, StatusEnum status, Date date,
			List<TransactionDetailApi> transactionDetail) {
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

	public List<TransactionDetailApi> getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(List<TransactionDetailApi> transactionDetail) {
		this.transactionDetail = transactionDetail;
	}

	public static class Builder {
		private Long id;
		private ClientApi client;
		private SellerApi seller;
		private StatusEnum status = StatusEnum.PENDING;
		private Date date;
		private List<TransactionDetailApi> transactionDetail;

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setClient(ClientApi client) {
			this.client = client;
			return this;
		}

		public Builder setSeller(SellerApi seller) {
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

		public Builder setTransactionDetail(List<TransactionDetailApi> transactionDetailList) {
			this.transactionDetail = transactionDetailList;
			return this;
		}

		public Builder setTransactionDetail(TransactionDetailApi transactionDetail) {
			this.transactionDetail = Arrays.asList(transactionDetail);
			return this;
		}
		
		public TransactionApi build() {
			return new TransactionApi(id, client, seller, status, date, transactionDetail);
		}
	}
}