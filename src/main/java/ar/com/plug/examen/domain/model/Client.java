package ar.com.plug.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CLIENT")
public class Client extends EntityModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6498970451078281818L;

	@NotBlank
	@Size(min = 4, max = 32)
	private String identification;

	@NotBlank
	@Size(min = 4, max = 32)
	private String names;

	@NotBlank
	@Size(min = 4, max = 32)
	private String surnames;

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

}
