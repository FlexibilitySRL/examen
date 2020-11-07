package ar.com.plug.examen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(columnDefinition = "varchar(255)")
    private  String name;
    @Column(columnDefinition = "varchar(255)")
    private  String description;
    @Column(columnDefinition = "long")
    private  Long value;
    @Column(columnDefinition = "long")
    private  Long quantity;
    @Column(columnDefinition = "boolean")
    private  Boolean state;

    public Products(@JsonProperty("name")  String name,
                    @JsonProperty("description") String description,
                    @JsonProperty("value") Long value,
                    @JsonProperty("quantity") Long quantity,
                    @JsonProperty("state") Boolean state) {

        this.name = name;
        this.description = description;
        this.value = value;
        this.quantity = quantity;
        this.state = state;
    }

    public Products() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getValue() {
        return value;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Boolean getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", quantity=" + quantity +
                ", state=" + state +
                '}';
    }
}
