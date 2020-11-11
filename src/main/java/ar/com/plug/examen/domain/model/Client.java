package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    public Client(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name ="lastName")
    private String lastName;

    @Column (name = "document")
    private String document;

    @OneToMany(mappedBy= "client")
    private List<Purchase> purchases = new ArrayList<Purchase>();

    public Client(String name, String lastName, String document){
        this.name = name;
        this.lastName = lastName;
        this.document = document;
    }

    public String getName(){
        return this.name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getDocument(){
        return this.document;
    }

    public Long getId(){
        return this.id;
    }
}
