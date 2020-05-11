package ar.com.flexibility.examen.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="purchase_items")
public class PurchaseItem {
	
	@Id
 	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	Product product;
	
	int quantity;
	double price;
	
	@ManyToOne
	@JoinColumn(name = "purchase_id")
	Purchase purchase;
	
	public PurchaseItem() {
		super();
	}
	
	public PurchaseItem(Product product, int quantity, double price) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

}
