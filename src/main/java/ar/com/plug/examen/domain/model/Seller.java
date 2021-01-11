package ar.com.plug.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class Seller {
	
	@Id
	@Column(name="seller_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="seller_name")
	private String name;
	
}
