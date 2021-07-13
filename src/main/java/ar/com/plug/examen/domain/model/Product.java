package ar.com.plug.examen.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author Joan Rey
 */
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String code;
    
    public Product() {
    	
    }
    /**
     * Declaro constructor
     * @param id
     * @param name
     * @param code
     */
    public Product(long id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
