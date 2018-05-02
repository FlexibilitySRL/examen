package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ar.com.flexibility.examen.domain.model.Seller;

@JsonRootName(value = "seller")  //The @JsonRootName annotation is used – if wrapping is enabled – to specify the name of the root wrapper to be used.
@JsonIgnoreProperties(ignoreUnknown = false)
public class SellerApi {

    @JsonProperty
    private String nombre;
   
    @JsonProperty
    private String apellido;
    
    @JsonProperty
    private String id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SellerApi [nombre=" + nombre + ", apellido=" + apellido + ", id=" + id + "]";
    }

    public SellerApi(String nombre, String apellido, String id) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
    }

    public SellerApi() {
        super();
    }

    public SellerApi(Seller seller) {
        super();
        this.nombre = seller.getName();
        this.apellido = seller.getSurname();
        this.id = seller.getId() + "";
    }
    
    


    
}
