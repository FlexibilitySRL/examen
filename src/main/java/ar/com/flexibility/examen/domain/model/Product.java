package ar.com.flexibility.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
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
@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@JsonProperty
    private String name;
	
	@JsonProperty
    private String code;
	
	@JsonProperty
    private Double price;

    public Product() {}

    public Product(String name, String code, Double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }
    
    public Long getId() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getCode() {
    	return code;
    }
    
    public Double getPrice() {
    	return price;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setCode(String code) {
    	this.code = code;
    }
    
    public void setPrice(Double price) {
    	this.price = price;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', code='%s', price='%s']",
                id, name, code, price);
    }
}