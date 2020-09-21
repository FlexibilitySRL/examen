package ar.com.flexibility.examen.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@Entity
@Immutable
@Table(name = "clients")
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
    @Column(name="names")
    public String names;
    
    @Column(name="identification")
    public String identification;
    
    @Column(name="cellphone")
    public String cellphone;
    
    @Column(name="country")
    public String country;
    
    @Column(name="enterprise")
    public String enterprise;
	
	public Client() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	
}
