package ar.com.plug.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Este recurso representa a un cliente
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	private Long id;


	private String firstName;
	private String middletName;
	private String lastName;
	private String salutation;
	private String cuit;
	private String addressL1;
	private String addressL2;
	private String addressL3;

}
