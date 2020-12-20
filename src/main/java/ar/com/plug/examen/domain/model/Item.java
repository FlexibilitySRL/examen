package ar.com.plug.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    @ManyToOne
    @JoinColumn(name="operation_id", nullable=false)
    private Operation operation;
    
    @OneToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	private Integer amount;

}
