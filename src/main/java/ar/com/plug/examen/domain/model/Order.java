package ar.com.plug.examen.domain.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "orders")
public class Order {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @OneToOne(fetch = FetchType.LAZY)
	    @JsonIgnore
	    private Customer customer;
	    
	    @OneToOne(fetch = FetchType.LAZY)
	    @JsonIgnore
	    private Seller seller;
	    
	    
	    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    private List<OrderItem> orderItems;
	    
	    
	    @JsonIgnore
	    private LocalDateTime registerDate;
	    
	    	    
	    @Enumerated(EnumType.STRING)
	    private OrderStatus status;
	    
	    

	    public Order() {
	    	
			this.registerDate = LocalDateTime.now();
			this.status = OrderStatus.REVIEW;
	    }



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



		public Seller getSeller() {
			return seller;
		}



		public void setSeller(Seller seller) {
			this.seller = seller;
		}



		public List<OrderItem> getOrderItems() {
			return orderItems;
		}



		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}



		public LocalDateTime getRegisterDate() {
			return registerDate;
		}



		public void setRegisterDate(LocalDateTime registerDate) {
			this.registerDate = registerDate;
		}



		public OrderStatus getStatus() {
			return status;
		}



		public void setStatus(OrderStatus status) {
			this.status = status;
		}


 
 
 
}
