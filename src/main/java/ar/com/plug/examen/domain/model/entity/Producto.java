package ar.com.plug.examen.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.JoinColumn;


@Entity
@Table(name="producto")
@Data
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Long id;
	
	@Column(name = "nombre_producto", length = 35)
	private String nombreProducto;
	
	@Column(name = "precio")
	private Long precio;
	
    
    @JoinTable(
        name = "rel_producto_compra",
        joinColumns = @JoinColumn(name = "fk_producto", nullable = false),
        inverseJoinColumns = @JoinColumn(name="fk_compra", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Compra> compras;
	
    public void addCompra(Compra compra){
        if(this.compras == null){
            this.compras = new ArrayList<>();
        }
        this.compras.add(compra);
    }

	
}
