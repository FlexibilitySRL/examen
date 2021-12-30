package ar.com.plug.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CompraDto implements Serializable {

    private Integer id;
    private String descripcion;
    private String observacion;
    private Date createAt;
    private Double total;
    private Boolean status;

    @JsonIgnoreProperties(value = {"compras", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    private ClienteDto cliente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ItemCompraDto> items;

    public CompraDto() {
        items = new ArrayList<>();
    }
}