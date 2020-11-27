package ar.com.plug.examen.domain.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "order_item")
public class OrderItem {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

 
	    @OneToOne(fetch = FetchType.LAZY)
	    @JsonIgnore
	    private Order order;

	    @OneToOne(fetch = FetchType.LAZY)
	    @JsonIgnore
	    private Product product;

	    private int quantity;

	    private float price;

	    private float total;
	    
	    private Date registerDate;

	    
	    
	    public OrderItem() { }


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Order getOrder() {
			return order;
		}


		public void setOrder(Order order) {
			this.order = order;
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


		public float getPrice() {
			return price;
		}


		public void setPrice(float price) {
			this.price = price;
		}


		public float getTotal() {
			return total;
		}


		public void setTotal(float total) {
			this.total = total;
		}


		public Date getRegisterDate() {
			return registerDate;
		}


		public void setRegisterDate(Date registerDate) {
			this.registerDate = registerDate;
		}

 
 
}
