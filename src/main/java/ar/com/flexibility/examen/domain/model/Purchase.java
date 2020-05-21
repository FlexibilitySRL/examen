package ar.com.flexibility.examen.domain.model;

import static ar.com.flexibility.examen.utils.PurchaseStatus.IN_PROGRESS;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ar.com.flexibility.examen.utils.PurchaseStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "purchases")
@EqualsAndHashCode(exclude = { "products" })
@ToString(exclude = { "products" })
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(required = false, hidden = true)
	private Long id;

	private Date createDate = new Date();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
	@JoinTable(name = "purchase_product", joinColumns = { @JoinColumn(name = "purchase_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	private Set<Product> products = new HashSet<Product>();

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "seller_id", nullable = false)
	private Seller seller;

	@Enumerated(EnumType.STRING)
	private PurchaseStatus status = IN_PROGRESS;

}