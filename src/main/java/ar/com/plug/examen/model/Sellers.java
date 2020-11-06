package ar.com.plug.examen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Sellers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(columnDefinition = "varchar(255)")
    private  String name;
    @Column(columnDefinition = "varchar(255)")
    private  String lastname;
    @Column(columnDefinition = "long")
    private  Long document;
    @Column(columnDefinition = "long")
    private  Long phone;
    @Column(columnDefinition = "boolean")
    private  Boolean state;

    public Sellers(@JsonProperty("name") String name,
                   @JsonProperty("lastname") String lastname,
                   @JsonProperty("document") Long document,
                   @JsonProperty("phone") Long phone,
                   @JsonProperty("state") Boolean state) {

        this.name = name;
        this.lastname = lastname;
        this.document = document;
        this.phone = phone;
        this.state = state;
    }

    public Sellers() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getDocument() {
        return document;
    }

    public Long getPhone() {
        return phone;
    }

    public Boolean getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Sellers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", document=" + document +
                ", phone=" + phone +
                ", state=" + state +
                '}';
    }
}
