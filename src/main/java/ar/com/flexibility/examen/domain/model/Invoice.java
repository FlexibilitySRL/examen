package ar.com.flexibility.examen.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "INVOICES")
public class Invoice implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7327522417932899942L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_invoice")
	@SequenceGenerator(sequenceName = "SEQ_INVOICE", name = "seq_invoice", allocationSize = 1, initialValue = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@Column(name = "invoiceDate")
	@NotNull
	private Date invoiceDate;
	
	@Column(name = "subtotal")
	@NotNull
	private Double subtotal;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "status")
	private InvoiceStatusEnum status;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "invoice")
	private Set<InvoiceItem> items;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public InvoiceStatusEnum getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatusEnum status) {
		this.status = status;
	}

	public Set<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(Set<InvoiceItem> items) {
		this.items = items;
	}
	
	public void addItems(InvoiceItem item) {
		if (items == null) {
			items = new HashSet<>();
		}
		items.add(item);
		item.setInvoice(this);
	}
}
