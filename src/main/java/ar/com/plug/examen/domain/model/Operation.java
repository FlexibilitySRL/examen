package ar.com.plug.examen.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private LocalDateTime date; // LocalDateTime.now()
	@ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
	@JsonIgnoreProperties("operations")
	private Customer customer;
	
	@OneToMany(mappedBy="operation")
	@JsonIgnoreProperties("operation")
	private List<Product> products;
	
	private Float total;
	
	private Boolean state;
	
	public void setTotal() {
		this.total = (float) 0;
		for (Product p : products) {
			this.total = this.total + p.getPrice();
		}
	}	
	
//	public void setTotal() {
//		this.total = (float) 0;
//		for (Item i : items) {
//			this.total = this.total + i.getProduct().getPrice();
//		}
//	}

}
