package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "purchase_items")
public class PurchaseItem extends AbstractPersistentObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Product product;
	
	public PurchaseItem() {
	}
	
	public PurchaseItem(Integer quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}
	
	public PurchaseItem(Long id, Integer quantity, Product product) {
		super(id);
		this.quantity = quantity;
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal calculateTotal() {
		return getProduct().getPrice().multiply(new BigDecimal(getQuantity()), MathContext.DECIMAL32);
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getTotal() {
		return this.getProduct().getPrice().multiply(new BigDecimal(this.getQuantity()), MathContext.DECIMAL32);
	}

}
