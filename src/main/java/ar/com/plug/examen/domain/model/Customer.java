package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Customer {

     @Id
     private String id;

     @Column(name = "nombre")
     private String name;

     @Column(name = "apellido")
     private String lastName;

     @Column(name = "correo")
     private String email;

     @Column(name = "telefono")
     private String phoneNumber;

     @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
     private List<Transaction> transactions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
