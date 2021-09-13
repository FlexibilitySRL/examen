package ar.com.plug.examen.domain.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="compra")
@Data
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compra")
	private Long id;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "aprobada")
	private Boolean aprobada = false;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
	
	
    @ManyToMany(mappedBy = "compras",
    		cascade = CascadeType.ALL)
    private List<Producto> productos;
	
	public void addProducto(Producto producto) {
		if (productos==null) {
			productos = new ArrayList<Producto>();
		}
		productos.add(producto);
		producto.addCompra(this);
	}

	
	
}
