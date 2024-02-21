package ar.com.plug.examen.infrastructure.rest.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class ResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Codigo de respuesta", example = "C200", required = false)
    private String code;

    @Schema(description = "Mensaje descriptivo", example = "Operacion existosa", required = false)
    private String message;

}
