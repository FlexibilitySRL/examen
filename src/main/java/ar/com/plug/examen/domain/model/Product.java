package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends AbstractPersistentObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private BigDecimal price;
	
	public Product(Long id, String name, BigDecimal price) {
		super(id);
		this.name = name;
		this.price = price;
	}

	public Product() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
