package ar.com.plug.examen.app.api;

import lombok.Data;

@Data
public class ProductApi {

  private Long id;
  private String name;
  private String description;
  private Double price;
  private Long stock;
}
