/**
 * 
 */
package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author rosalizaracho
 *
 */
@Entity
@Table(name="purchase_table")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPurchase;
	
	@Access(AccessType.FIELD)
	@OneToMany(targetEntity= Order.class,mappedBy="purchase",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Order> orders = new ArrayList<>();
	
	@Access(AccessType.FIELD)
	@ManyToOne(targetEntity= Client.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idClient")
	Client client; 
	
	@Temporal(TemporalType.DATE)
	private Date dateOfPurchase;
	
	private Double total;
	
	public Purchase() {}
	
	public Purchase(Client client, Date date, Double total) {
		this.dateOfPurchase = date;
	    this.client = client;
	    this.total = total;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Long getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Long idPurchase) {
		this.idPurchase = idPurchase;
	}

	public void addOrder(Order order) {
		this.orders.add(order);
		
	}
	
}
