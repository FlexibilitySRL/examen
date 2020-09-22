package ar.com.flexibility.examen.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import ar.com.flexibility.examen.domain.enums.CustomerStatus;

public class CustomerDTO {

	private Long id;
	
    @Size( min = 8 , max = 8, message = "document_size_invalid")
    private String document;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
    
    @Email
    private String email;

    private CustomerStatus status;
    
	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}
    
}
