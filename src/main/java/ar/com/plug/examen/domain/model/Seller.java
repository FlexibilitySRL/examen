package ar.com.plug.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@JsonProperty(required=true)
	@NotEmpty
	@NotNull
	private String name;
	
	@Column
	@Email
	private String email;
	
	@Column
	@JsonProperty("company_name")
	@NotEmpty
	@NotNull
	private String companyName;
	
	@Column
	@JsonProperty(value="document_id",required=true)	
	@Positive
	@NotNull
	private long documentId;
	
	
}
