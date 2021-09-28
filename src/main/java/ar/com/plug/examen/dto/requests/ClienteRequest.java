package ar.com.plug.examen.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String dni;
}
