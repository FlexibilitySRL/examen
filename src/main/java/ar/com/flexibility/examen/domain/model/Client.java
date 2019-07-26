package ar.com.flexibility.examen.domain.model;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Product> products;

    public Client(){
        products = new ArrayList<>();
    }

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
        products = new ArrayList<>();
    }

    public void addProduct(Product product){
        product.setClient(this);
        products.add(product);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
