package ar.com.plug.examen.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String dni;
}
