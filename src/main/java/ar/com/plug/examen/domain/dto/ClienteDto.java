package ar.com.plug.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ClienteDto implements Serializable {

    private Integer id;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 4, max = 12, message = "el tamaño tiene que estra entre 4 y 12")
    private String nombre;

    @NotEmpty(message = "no puede estar vacío")
    private String apellido;

    @NotEmpty(message = "no puede estar vacío")
    @Email(message = "no es una dirección de correo bien formada")
    private String email;

    @NotNull(message = "no puede estar vacío")
    private Date createAt;

    private String foto;

    @JsonIgnoreProperties({"cliente", "hibernateLazyInitializer", "handler"})
    private List<CompraDto> compras;

    public ClienteDto() {
        this.compras = new ArrayList<>();
    }
}