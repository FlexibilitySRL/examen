package ar.com.plug.examen.dto.responses;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Compra;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionResponse {

    List<Compra> compras;
    Long idTransaccion;
    private String estado;
    private Cliente cliente;
    private Long totalTransaccion;
}
