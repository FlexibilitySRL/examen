package ar.com.flexibility.examen.domain.model.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long quantity;
	private BigDecimal value;

	@JoinColumn(name = "id_invoice", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Invoice invoice;

	@JoinColumn(name = "id_product", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Product product;
}
