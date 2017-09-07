package ar.com.flexibility.examen.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Falta detalle de purchase
 * TODO: crear standar meta {
 * }
 * data {
 * }
 * crear clase para encapsular estos datas con generico <?> 
 */

/**
 * 
 * @author hackma
 * @version 0.1
 */
@Entity
@JsonRootName(value = "invoice")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Invoice {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@JsonProperty
	@ManyToOne(optional=false)
    @JoinColumn(name="CUSTOMER_ID",referencedColumnName="ID")
	private Customer customer;
	
	@JsonProperty
	private Date date;
	
	public Invoice() {}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", customer=" + customer + ", date=" + date + "]";
	}
}