package ar.com.flexibility.examen.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import ar.com.flexibility.examen.domain.enums.CustomerStatus;

@Entity
@Table(name="CUSTOMERS")
public class Customer{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "document_empty")
    @Column(name = "DOCUMENT" , unique = true ,length = 8, nullable = false)
    private String document;
    
    @NotEmpty(message = "first_name_empty")
    @Column(name="FIRST_NAME", nullable=false)
    private String firstName;

    @NotEmpty(message = "last_name_empty")
    @Column(name="LAST_NAME", nullable=false)
    private String lastName;
    
    @NotEmpty(message = "email_empty")
    @Email(message = "email_invalid")
    @Column(name="EMAIL",unique=true, nullable=false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS",nullable=false)
    private CustomerStatus status;
    
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Purchases> purchases;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}
    
}
