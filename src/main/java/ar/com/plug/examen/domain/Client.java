package ar.com.plug.examen.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Client {

    private Integer id;
    private String name;
    private String lastName;
    private String docNumber;
    private String email;

}
