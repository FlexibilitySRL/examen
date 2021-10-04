package ar.com.plug.examen.dto.responses;

import ar.com.plug.examen.domain.model.Compra;
import java.util.List;
import lombok.Data;

@Data
public class ListaComprasResponse {
    List<Compra> compras;
    Long transaccionId;
}
