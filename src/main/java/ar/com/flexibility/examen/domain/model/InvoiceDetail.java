package ar.com.flexibility.examen.domain.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonRootName(value = "invoice")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceDetail {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@JsonProperty
	@OneToOne(optional=false)
    @JoinColumn(name="INVOICE_ID",referencedColumnName="ID")
	private Invoice invoice;
	
	@JsonProperty
	@ManyToOne(optional=false)
    @JoinColumn(name="PRODUCT_ID",referencedColumnName="ID")
	private Product product;
	
	@JsonProperty
	private BigInteger amount;
	
	@JsonProperty
	private BigDecimal price;

	public InvoiceDetail() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "InvoiceDetail [id=" + id + ", invoice=" + invoice + ", product=" + product + ", amount=" + amount
				+ ", price=" + price + "]";
	}
}