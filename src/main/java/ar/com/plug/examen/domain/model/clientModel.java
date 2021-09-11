package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class clientModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    private String identification;

    private String name;

    private String email;

    public clientModel(String identification, String name, String email) {
        this.identification = identification;
        this.name = name;
        this.email = email;
    }

    public clientModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
