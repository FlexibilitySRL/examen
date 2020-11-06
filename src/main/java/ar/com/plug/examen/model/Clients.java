package ar.com.plug.examen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    @Column(columnDefinition = "varchar(255)")
    private  String name;
    @Column(columnDefinition = "varchar(255)")
    private  String lastName;
    @Column(columnDefinition = "integer")
    private  Integer document;
    @Column(columnDefinition = "integer default 25")
    private  Integer phone;
    @Column(columnDefinition = "varchar(255)")
    private  String address;
    @Column(columnDefinition = "boolean")
    private  Boolean state;

    public Clients(@JsonProperty("name") String name,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("document") Integer document,
                   @JsonProperty("phone") Integer phone,
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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getDocument() {
        return document;
    }

    public Integer getPhone() {
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
