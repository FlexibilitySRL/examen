package ar.com.flexibility.examen.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * Class representing a product entity
 */
@Data
@Entity(name = "Product")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseObject {
	private static final long serialVersionUID = 978871380664119094L;
	private String name;
}