package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="APPROVE")
public class Approve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String transactionId;

    private String description;

    public Approve(){

    }

    public Approve(String transactionId, String descripcion){
        this.transactionId=transactionId;
        this.description=descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Approve{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Approve approve = (Approve) o;
        return id.equals(approve.id) &&
                transactionId.equals(approve.transactionId) &&
                description.equals(approve.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionId, description);
    }
}
