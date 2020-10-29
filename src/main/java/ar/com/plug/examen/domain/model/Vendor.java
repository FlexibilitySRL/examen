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
public class Vendor {

	@Id
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String username;

}
