package ar.com.plug.examen.domain.model;

import org.checkerframework.checker.fenum.qual.Fenum;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="last_name")
    private String lastName;
    @OneToMany
    @JoinColumn(name = "client_id")
    @Transient
    private List<Sale> sales;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sales=" + sales +
                '}';
    }
}
