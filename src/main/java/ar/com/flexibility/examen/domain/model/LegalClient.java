package ar.com.flexibility.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LEGALCLIENTS")
public class LegalClient extends Client {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public <R> R accept(ClientVisitor<R> visitor) {
		return visitor.visit(this);
	}

}
