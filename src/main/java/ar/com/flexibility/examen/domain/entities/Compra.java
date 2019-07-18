package ar.com.flexibility.examen.domain.entities;

import ar.com.flexibility.examen.domain.entities.Cliente;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.*;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="COMPRA")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Compra implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    
    @Column(name="APROBADO")
    private boolean aprobado;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Producto.class, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="producto_id")
    private Producto prod;


    public Compra() {
    }

    public Compra(int id, boolean aprobado, Cliente cliente,Producto producto) {
        this.id = id;
        this.aprobado = aprobado;
        this.cliente = cliente;
        this.prod = producto;
    }
    
    public Compra(Compra compra, Cliente cliente, Producto producto) {
        this.id = compra.getId();
        this.aprobado = compra.isAprobado();
        this.cliente = cliente;
        this.prod = producto;
    }
    
    

    public Compra(Compra compra) {
		this.id = compra.getId();
		this.aprobado = compra.isAprobado();
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

	public Producto getProducto() {
		return prod;
	}

	public void setProducto(Producto producto) {
		this.prod = producto;
	}

}
