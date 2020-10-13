package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ar.com.plug.examen.domain.enums.PurchaseStatus;

@Entity
@Table(name = "purchases")
public class Purchase extends AbstractPersistentObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference(value = "client")
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference(value = "seller")
	private Seller seller;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="purchase_id")
	private List<PurchaseItem> items;
	
	@Enumerated(EnumType.STRING)
	private PurchaseStatus status;
	
	public Purchase() {
		this.items = new ArrayList<PurchaseItem>();
		this.status = PurchaseStatus.PENDING;
	}
	
	public Purchase(String description, Client client, Seller seller) {
		this();
		this.description = description;
		this.client = client;
		this.seller = seller;
	}
	
	
	public Purchase(Long id, @NotEmpty String description, Client client, Seller seller, List<PurchaseItem> items, PurchaseStatus status) {
		super(id);
		this.description = description;
		this.client = client;
		this.seller = seller;
		this.items = items;
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	@XmlTransient
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<PurchaseItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseItem> items) {
		this.items = items;
	}

	public void addItem(PurchaseItem item) {
		this.getItems().add(item);
	}
	
	public PurchaseStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseStatus status) {
		this.status = status;
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (PurchaseItem itemFactura : getItems()) {
			total = total.add(itemFactura.calculateTotal(), MathContext.DECIMAL32);
		}
		return total;
	}

}
