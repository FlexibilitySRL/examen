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
@JsonRootName(value = "customer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@JsonProperty
    private String firstName;
	@JsonProperty
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Long getId() {
    	return id;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastname() {
    	return lastName;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}