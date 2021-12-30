package ar.com.plug.examen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto implements Serializable {
    private Integer id;
    private String nombre;
    private Double precio;
    private Date createdAt;
}