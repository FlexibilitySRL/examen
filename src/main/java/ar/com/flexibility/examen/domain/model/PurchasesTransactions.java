package ar.com.flexibility.examen.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@Entity
@Table(name = "purchases_transactions")
public class PurchasesTransactions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
    @Column(name="comments")
	public String comments;
    
    @Column(name="date")
	public String date;
	
    @ManyToOne()
    @JoinColumn(name="fk_product", referencedColumnName = "id", insertable = false, updatable = false) 
	public Product fkProduct;
	
    @ManyToOne()
    @JoinColumn(name="fk_client", referencedColumnName = "id", insertable = false, updatable = false) 
	public Client fkClient;
	
	public PurchasesTransactions() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Product getFkProduct() {
		return fkProduct;
	}

	public void setFkProduct(Product fkProduct) {
		this.fkProduct = fkProduct;
	}

	public Client getFkClient() {
		return fkClient;
	}

	public void setFkClient(Client fkClient) {
		this.fkClient = fkClient;
	}
}
