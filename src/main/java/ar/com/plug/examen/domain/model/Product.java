package ar.com.plug.examen.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "product")
public class Product {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

		private String name;

	    private String description;
 
	    private Float price;
	    
	    private LocalDateTime registerDate;



	    public Product() { 
	    	this.registerDate = LocalDateTime.now();
	    }


	    public Product(Long id, String name, String description,float price) { 
	    	this.id = id;
	    	this.name = name ;
	    	this.description = description ;
	    	this.price = price;
	    	this.registerDate = LocalDateTime.now();
	    }
	    
	    
	    public Product(String name, String description,float price) { 
	    	this.name = name ;
	    	this.description = description ;
	    	this.price = price;
	    	this.registerDate = LocalDateTime.now();
	    }

	    
	    
	    
		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public Float getPrice() {
			return price;
		}


		public void setPrice(Float price) {
			this.price = price;
		}

		public LocalDateTime getRegisterDate() {
			return registerDate;
		}


		public void setRegisterDate(LocalDateTime registerDate) {
			this.registerDate = registerDate;
		}
     
 
 
}
