package ar.com.plug.examen.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "seller")
public class Seller {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

		private String firstName;

	    private String lastName;
 
	    public Seller() { }
     
		 
	    public Seller(Long id, String firstName, String lastName) {
    		this.id=id;
    		this.firstName = firstName;
 			this.lastName = lastName;
  		}
    
	    public Seller(String firstName, String lastName) {
	 		this.firstName = firstName;
			this.lastName = lastName;
		}
	    
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	 
 
}
