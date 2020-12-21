package ar.com.plug.examen.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String companyName; // if is a person, will be the fullname.
	@NotNull
	private String taxIdNumber;
	@NotNull
	private String email;
	private String phoneNumber;

	@OneToMany(mappedBy = "customer")
	private List<Operation> operations;
}
