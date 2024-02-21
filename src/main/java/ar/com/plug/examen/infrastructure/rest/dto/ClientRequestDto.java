package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import ar.com.plug.examen.domain.Client;
import ar.com.plug.examen.shared.config.MenssageResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClientRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank(message = MenssageResponse.C401)
    private String name;
    private String lastName;
    private String docNumber;
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
