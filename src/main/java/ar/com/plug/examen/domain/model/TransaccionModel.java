package ar.com.plug.examen.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "transacciones")

public class TransaccionModel {

    //atributos
    private Long id;
    private String serie;
    private Integer numero;
    @ManyToOne
    private ClienteModel cliente;
    @ManyToOne
    private VendedorModel vendedor;
    private Integer estado;

    //constructor
    public TransaccionModel(Long id, String serie, Integer numero, Integer cliente, Integer vendedor, Integer estado) {
        this.id = id;
        this.serie = serie;
        this.numero = numero;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.estado = estado;
    }
    
    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCliente(){
        return cliente;
    }

    public void setCliente(Integer cliente){
        this.cliente = cliente;
    }

    public String getVendedor(){
        return vendedor;
    }

    public void setVendedor(Integer vendedor){
        this.vendedor = vendedor;
    }
    
}