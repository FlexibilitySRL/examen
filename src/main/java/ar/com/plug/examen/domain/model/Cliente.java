/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.model;

/**
 * Declaracion de la clase Cliente 
 * @author G&L
 */
public class Cliente {
    
    private int id;
    private int DNI;
    private String nombre;
    private int telefono;
    private String direccion;
    private String razon;
    
    public Cliente(){
        
    }
    /**
     * Declaracion del constructor Cliente
     * @param id
     * @param DNI
     * @param nombre
     * @param telefono
     * @param direccion
     * @param razon 
     */
    public Cliente(int id, int DNI, String nombre, int telefono, String direccion, String razon) {
        this.id = id;
        this.DNI = DNI;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon = razon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    
    
}
