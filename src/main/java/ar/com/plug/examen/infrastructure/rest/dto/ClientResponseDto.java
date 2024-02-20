package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import ar.com.plug.examen.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ClientResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String lastName;
    private String docNumber;
    private String email;

    public ClientResponseDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.docNumber = client.getDocNumber();
        this.email = client.getEmail();
    }
}
