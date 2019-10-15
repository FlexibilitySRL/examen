package ar.com.flexibility.examen.domain.model.dto;

import ar.com.flexibility.examen.domain.model.LineItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String description;

    private BigDecimal price;

}
