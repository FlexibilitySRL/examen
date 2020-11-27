package ar.com.plug.examen.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "customer")
public class Customer {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

		private String firstName;

	    private String lastName;
 
	    private LocalDateTime registerDate;
	    

	    
	    public Customer(Long id, String firstName, String lastName) {
	    		this.id=id;
	    		this.firstName = firstName;
	 			this.lastName = lastName;
	 			this.registerDate = LocalDateTime.now();
	 		}
	    
	    public Customer(String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.registerDate = LocalDateTime.now();
		}


		public Customer() { 
			this.registerDate = LocalDateTime.now();
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


		public LocalDateTime getRegisterDate() {
			return registerDate;
		}


		public void setRegisterDate(LocalDateTime registerDate) {
			this.registerDate = registerDate;
		}

 
 
}
