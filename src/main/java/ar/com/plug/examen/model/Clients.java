package ar.com.plug.examen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(columnDefinition = "varchar(255)")
    private  String name;
    @Column(columnDefinition = "varchar(255)")
    private  String lastName;
    @Column(columnDefinition = "integer")
    private  Long document;
    @Column(columnDefinition = "integer")
    private  Long phone;
    @Column(columnDefinition = "varchar(255)")
    private  String address;
    @Column(columnDefinition = "boolean")
    private  Boolean state;

    public Clients(@JsonProperty("name") String name,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("document") Long document,
                   @JsonProperty("phone") Long phone,
                   @JsonProperty("address") String address,
                   @JsonProperty("state") Boolean state) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.phone = phone;
        this.address = address;
        this.state = state;
    }

    public Clients() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getDocument() {
        return document;
    }

    public Long getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document=" + document +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", state=" + state +
                '}';
    }

}
