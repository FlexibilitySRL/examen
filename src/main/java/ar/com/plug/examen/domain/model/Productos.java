/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.model;

/**
 * Declaracion de la clase Productos
 * @author G&L
 */
public class Productos {
    private int id;
    private String codigo;
    private String nombre;
    private String Proveedor;
    private int Stock;
    private double precio;

    public Productos(){
        
    }
    /**
     * Declaracion del constructor Productos
     * @param id
     * @param codigo
     * @param nombre
     * @param Proveedor
     * @param Stock
     * @param precio 
     */
    public Productos(int id, String codigo, String nombre, String Proveedor, int Stock, double precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.Proveedor = Proveedor;
        this.Stock = Stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    

}