package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


/**
 *
 * @author Joan Rey
 */

@Entity
@Data
public class CustomerProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long productId;
    @Transient
    private String productName;
    @JsonIgnore//it is necesary for avoid infinite recursion
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Customer.class)
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;
	public void setProductName(String productName2) {
		// TODO Auto-generated method stub
		
	}
	public long getProductId() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Object setCustomer(Customer input) {
		// TODO Auto-generated method stub
		return null;
	}
}
