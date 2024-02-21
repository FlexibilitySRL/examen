package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import ar.com.plug.examen.domain.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClientResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Identificador unico del cliente", example = "1")
    private Integer id;
    @Schema(description = "Nombre del cliente", example = "jose")
    private String name;
    @Schema(description = "Apellido del cliente", example = "perez")
    private String lastName;
    @Schema(description = "Documento del cliente", example = "123456789")
    private String docNumber;
    @Schema(description = "Email del cliente", example = "email@email.com")
    private String email;

    public ClientResponseDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.docNumber = client.getDocNumber();
        this.email = client.getEmail();
    }
}
