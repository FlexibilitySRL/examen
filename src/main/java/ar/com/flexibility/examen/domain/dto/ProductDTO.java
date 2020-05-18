package ar.com.flexibility.examen.domain.dto;

import lombok.Data;
import lombok.ToString;

/**
 * A DTO for {@link ar.com.flexibility.examen.domain.service.impl.ProductService} requests
 */
@Data
@ToString(callSuper = true)
public class ProductDTO {
	private String name;
}
