package ar.com.plug.examen.domain.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductoRestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2489538174167345916L;

	private Long id;
	
	private String nombreProducto;
	
	private Long precio;
	

}
