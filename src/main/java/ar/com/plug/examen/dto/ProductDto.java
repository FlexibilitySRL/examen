package ar.com.plug.examen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

	private Long id;
	
	private String name;
	
	private String description;
	
	private int value;
	
	public ProductDto() {
		System.out.print("");
	}
}
