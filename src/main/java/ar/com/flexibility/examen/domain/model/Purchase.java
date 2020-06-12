package ar.com.flexibility.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Purchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer customer;
    
    private boolean aporoved = false;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the aporoved
     */
    public boolean isAporoved() {
        return aporoved;
    }

    /**
     * @param aporoved the aporoved to set
     */
    public void setAporoved(boolean aporoved) {
        this.aporoved = aporoved;
    }
    
}
