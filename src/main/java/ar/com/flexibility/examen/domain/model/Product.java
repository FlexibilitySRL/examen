package ar.com.flexibility.examen.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
    @Column(name="names")
	public String names;
    
    @Column(name="values")
	public String values;
    
    @Column(name="reference_number")
	public String numberReferences;
	
	public Product() {
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
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getNumberReferences() {
		return numberReferences;
	}
	public void setNumberReferences(String numberReferences) {
		this.numberReferences = numberReferences;
	}
}
