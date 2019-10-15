package ar.com.flexibility.examen.domain.model.dto;

import ar.com.flexibility.examen.domain.model.Account;
import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.model.LineItem;
import ar.com.flexibility.examen.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private OrderStatus status;

    private BigDecimal total;

    private List<LineItem> lineItems;

}
