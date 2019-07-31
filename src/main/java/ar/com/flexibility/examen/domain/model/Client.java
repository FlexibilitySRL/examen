package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<Purcharse> purcharses;

    private String name;
    private String surname;


    public Client(){
        purcharses = new ArrayList<>();
    }

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.purcharses = new ArrayList<>();
    }

    public void addPurcharse(Purcharse purcharse) {
        purcharses.add(purcharse);
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

    public List<Purcharse> getPurcharses() {
        return purcharses;
    }

    public void setPurcharses(List<Purcharse> purcharses) {
        this.purcharses = purcharses;
    }
}
