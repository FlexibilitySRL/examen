package ar.com.plug.examen.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity(name = "Order_Purchase")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(targetEntity = Product.class,fetch = FetchType.EAGER)
	private List<Product> products;

	@OneToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "id")
	private Seller seller;

	@Column
	private String status;

	@Column
	@JsonProperty(value = "creation_date")
	@NotNull
	private Date creationDate;

	@Column
	@JsonProperty(required = true)
	@NotNull
	@Positive
	private Double amount;

	@Column
	@JsonProperty(value = "operation_id")
	private String operationId;

	@Column
	@JsonProperty(value = "modification_date")
	private Date modificationDate;

}
