package ar.com.flexibility.examen.domain.model.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal total;
	private LocalDateTime created = LocalDateTime.now();
	private Boolean state;

	@JoinColumn(name = "id_client")
	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;

	@Transient
	private List<InvoiceDetail> invoiceDetail;

}
