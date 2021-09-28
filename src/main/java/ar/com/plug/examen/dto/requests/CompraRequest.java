package ar.com.plug.examen.dto.requests;

import ar.com.plug.examen.domain.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequest {
    private Long productoId;
    private int cantidad;
    private Long transaccionId;
}
