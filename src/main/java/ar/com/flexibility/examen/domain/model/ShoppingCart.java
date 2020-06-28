package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCart {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartProduct> products;
    
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus shoppingCartStatus;
    
    public ShoppingCart() {
    	this.products = new ArrayList<ShoppingCartProduct>();
    	this.shoppingCartStatus  = ShoppingCartStatus.PENDING;
    }
    
    public void removeProduct(ShoppingCartProduct shoppingCartProduct) {
    	this.products.remove(shoppingCartProduct);
    } 
    public ShoppingCartStatus getShoppingCartStatus() {
		return shoppingCartStatus;
	}
    
    public void setShoppingCartStatus(ShoppingCartStatus shoppingCartStatus) {
		this.shoppingCartStatus = shoppingCartStatus;
	}
    
    public Customer getCustomer() {
		return customer;
	}
    
    public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    public List<ShoppingCartProduct> getProducts() {
		return products;
	}
    
    public void setProducts(List<ShoppingCartProduct> products) {
		this.products = products;
	}
    
    public void addProduct(ShoppingCartProduct shoppingCartProduct){
    	this.products.add(shoppingCartProduct);
    }
    

}
