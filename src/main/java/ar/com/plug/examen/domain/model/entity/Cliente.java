package ar.com.plug.examen.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="cliente")
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
	private Long id;
	
	@OneToMany(mappedBy = "cliente",
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	private List<Compra> compras;
	
	@Column(name = "nombre")
	private String nombre;
	
	
	public void addCompra(Compra compra) {
		if (compras==null) {
			compras = new ArrayList<Compra>();
		}
		compras.add(compra);
		compra.setCliente(this);
	}
	
	

	
}
