package ar.com.flexibility.examen.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import ar.com.flexibility.examen.domain.enu.SaleStatus;

@Entity
@Table(name = "sale", indexes = { 
		@Index(name = "idx_sale_code", columnList = "code"), 
	}, uniqueConstraints = {
	    @UniqueConstraint (name = "uqx_sale_code", columnNames = "code"),
	})
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq")
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "sale_seq", sequenceName = "sale_seq")
	private Long id;
    @Column (name = "amount", nullable = false, precision = 3)
	private int amount;
    @Column (name = "code", nullable = false, length = 50)
	private String code;
    @Column(name = "date", nullable = false)
    @Temporal (TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "date_approved", nullable = true)
    @Temporal (TemporalType.TIMESTAMP)
    private Date dateApproved;
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private SaleStatus status;
    @Column (name = "value", nullable = false, precision = 100, scale = 2)
	private double value;
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", foreignKey = @ForeignKey (name = "fk_sale_client"))
	private Client client;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey (name = "fk_sale_product"))
	private Product product;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_id", foreignKey = @ForeignKey (name = "fk_sale_seller"))
	private Seller seller;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDateApproved() {
		return dateApproved;
	}
	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}
	public SaleStatus getStatus() {
		return status;
	}
	public void setStatus(SaleStatus status) {
		this.status = status;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
    
    
}
