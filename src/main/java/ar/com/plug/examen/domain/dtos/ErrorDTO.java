package ar.com.plug.examen.domain.dtos;

import lombok.Data;

@Data
public class ErrorDTO {

    private String name;
    private String value;

    public ErrorDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
