package ar.com.plug.examen.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Integer inventory;
    private Double price;
    private String barCode;
}
