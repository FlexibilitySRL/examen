package ar.com.plug.examen.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long client_id;

    @Column(name = "firstName")
    @NotNull(message = "lastName is required")
    @NotBlank(message = "firstName is required")
    private String firstName;

    @Column(name = "lastName")
    @NotNull(message = "lastName is required")
    @NotBlank(message = "lastName is required")
    private String lastName;

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
