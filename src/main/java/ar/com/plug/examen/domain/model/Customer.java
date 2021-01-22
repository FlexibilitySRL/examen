package ar.com.plug.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Customer {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String email;
	
	@Column
	@JsonProperty(required=true)
	private String firstname;
	
	@Column
	@JsonProperty(required=true)
	private String lastname;
	
	@Column
	@JsonProperty(value="document_id",required=true)	
	private long documentId;
	
	@Column
	@JsonProperty(value="mobile_phone")
	private String mobilePhone;
	
    
}
