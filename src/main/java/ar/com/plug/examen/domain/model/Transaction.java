package ar.com.plug.examen.domain.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRANSACTION")
public class Transaction extends EntityModel {

	private static final long serialVersionUID = 4659837110613651462L;

	public static enum TransactionStatusEnum {
		APPROVED, REJECTED, PENDING
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TransactionItem> items;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TransactionStatusEnum status;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<TransactionItem> getItems() {
		return items;
	}

	public void setItems(Set<TransactionItem> items) {
		this.items = items;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public TransactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}

}
