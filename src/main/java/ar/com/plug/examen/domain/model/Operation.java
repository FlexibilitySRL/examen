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
	private Customer customer;
	
	@OneToMany(mappedBy="operation")
	private List<Item> items;
	
	private Float total;
	
	private Boolean state;

}
