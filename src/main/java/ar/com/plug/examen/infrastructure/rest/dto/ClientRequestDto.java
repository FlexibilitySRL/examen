package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import ar.com.plug.examen.domain.Client;
import ar.com.plug.examen.shared.config.MenssageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClientRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Identificador unico del cliente", example = "1", required = false)
    private Integer id;

    @Schema(description = "Nombre del cliente", example = "jose", required = true)
    @NotBlank(message = MenssageResponse.C401)
    private String name;

    @Schema(description = "Apellido del cliente", example = "perez", required = false)
    private String lastName;

    @Schema(description = "Documento del cliente", example = "123456789", required = false)
    private String docNumber;

    @Schema(description = "Email del cliente", example = "email@email.com", required = true)
    @Email(message = MenssageResponse.C402)
    private String email;

    public Client toClient() {
        return Client.builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .docNumber(docNumber)
                .email(email)
                .build();
    }

}
