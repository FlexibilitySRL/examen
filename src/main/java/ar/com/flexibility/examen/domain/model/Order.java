/**
 * 
 */
package ar.com.flexibility.examen.domain.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ro
 *
 */
@Entity
@Table(name="order_table")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;
	
	@Access(AccessType.FIELD)
	@ManyToOne(targetEntity= Product.class,fetch = FetchType.LAZY)
	@JoinColumn(name="idProduct", nullable=false)
	private Product product;
	
	private Integer items;
	
	@Access(AccessType.FIELD)
	@ManyToOne(targetEntity= Purchase.class,fetch = FetchType.LAZY)
	@JoinColumn(name="idPurchase", nullable=false)
	private Purchase purchase;
	
	public Order() {}
	
	public Order(Product product, int items, Purchase purchase) {
		this.product = product;
		this.items = items;
		this.purchase = purchase;
	}

	public Long getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Integer getItems() {
		return items;
	}
	public void setItems(Integer items) {
		this.items = items;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
	
	
	
	
}
