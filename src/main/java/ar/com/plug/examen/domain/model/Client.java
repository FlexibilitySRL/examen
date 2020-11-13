package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.EmptyLastNameException;
import ar.com.plug.examen.domain.exceptions.EmptyNameException;
import ar.com.plug.examen.domain.exceptions.InvalidDocumentNumberException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a Client
 * @author Pablo Marrero
 *
 */

@Entity
@Table(name = "client")
@ApiModel("Model Client")
public class Client implements Serializable {

    public Client(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "the client's id", required = true)
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(value = "the client's name", required = true)
    private String name;

    @Column(name ="lastName")
    @ApiModelProperty(value = "the client's lastname", required = true)
    private String lastName;

    @Column (name = "document")
    @ApiModelProperty(value = "the client's document", required = true)
    private String document;

    @OneToMany(mappedBy= "client")
    private List<Purchase> purchases = new ArrayList<Purchase>();

    public Client(String name, String lastName, String document) throws EmptyNameException, EmptyLastNameException, InvalidDocumentNumberException {
        this.validateName(name);
        this.validateLastName(lastName);
        this.validateDocument(document);
    }

    private void validateDocument(String document) throws InvalidDocumentNumberException {
        if (document.isEmpty() || ! document.matches("\\d+")){
            throw new InvalidDocumentNumberException("The client document number is not valid.");
        }
        this.document = document;
    }

    private void validateLastName(String lastName) throws EmptyLastNameException {
        if (lastName.isEmpty()){
            throw new EmptyLastNameException("The client last name should not be empty.");
        }
        this.lastName = lastName;
    }

    private void validateName(String name) throws EmptyNameException {
        if (name.isEmpty()){
            throw new EmptyNameException("The client name should not be empty.");
        }
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return document.equals(client.document);
    }

    @Override
    public int hashCode() {
        return document.hashCode();
    }
}
